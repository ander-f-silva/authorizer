package br.com.nb.authorizer.domain;

import br.com.nb.authorizer.application.dto.AccountRef;
import br.com.nb.authorizer.application.dto.InputTransaction;
import br.com.nb.authorizer.application.dto.OutputAccount;
import br.com.nb.authorizer.domain.repository.Repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AuthorizationTransaction {
  public static final String MESSAGE_CARD_NOT_ACTIVE = "card-not-active";
  public static final String MESSAGE_INSUFFICIENT_LIMIT = "insufficient-limit";
  public static final String MESSAGE_HIGH_FREQUENCY_SMALL_INTERVAL =
      "high-frequency-small-interval";
  public static final String MESSAGE_DOUBLED_TRANSACTION = "doubled-transaction";

  private final Repository<Account> accountRepository;

  public AuthorizationTransaction(Repository<Account> accountRepository) {
    this.accountRepository = accountRepository;
  }

  public OutputAccount authorize(InputTransaction inputTransaction) {
    Account account = accountRepository.findOne();

    var transaction =
        new Transaction(
            inputTransaction.getTransaction().getMerchant(),
            inputTransaction.getTransaction().getAmount(),
            inputTransaction.getTransaction().getTime());

    var violations = verifyIfOperationHasViolation(account, transaction);

    if (!violations.isEmpty()) {
      return new OutputAccount(
          new AccountRef(account.isActiveCard(), account.getAvailableLimit()),
          violations.toArray(String[]::new));
    }

    account.authorizedTransaction(transaction);

    return new OutputAccount(new AccountRef(account.isActiveCard(), account.getAvailableLimit()));
  }

  private Set<String> verifyIfOperationHasViolation(Account account, Transaction transaction) {
    Map<String, Boolean> violations = new HashMap<>(4);

    violations.put(MESSAGE_CARD_NOT_ACTIVE, account.isNotActiveCard());
    violations.put(
        MESSAGE_INSUFFICIENT_LIMIT, account.hasNotSufficientLimit(transaction.getAmount()));
    violations.put(MESSAGE_DOUBLED_TRANSACTION, account.hasDoubledTransaction(transaction));
    violations.put(
        MESSAGE_HIGH_FREQUENCY_SMALL_INTERVAL, account.hasHighFrequencySmallInterval(transaction));

    Set<String> messages = new HashSet<>();

    for (var message : violations.keySet()) {
      if (violations.get(message)) {
        messages.add(message);
        return messages;
      }
    }

    return messages;
  }
}
