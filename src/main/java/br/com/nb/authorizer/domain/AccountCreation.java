package br.com.nb.authorizer.domain;

import br.com.nb.authorizer.application.dto.AccountRef;
import br.com.nb.authorizer.application.dto.InputAccount;
import br.com.nb.authorizer.application.dto.OutputAccount;
import br.com.nb.authorizer.domain.repository.Repository;

public class AccountCreation {
  public static final String MESSAGE_ACCOUNT_ALREADY_INITIALIZED = "account-already-initialized";

  private final Repository<Account> accountRepository;

  public AccountCreation(Repository<Account> accountRepository) {
    this.accountRepository = accountRepository;
  }

  public OutputAccount create(InputAccount inputAccount) {
    AccountRef accountRef = inputAccount.getAccountRef();

    if (accountRepository.exist()) {
      Account account = accountRepository.findOne();
      return new OutputAccount(
          new AccountRef(account.isActiveCard(), account.getAvailableLimit()),
          new String[] {MESSAGE_ACCOUNT_ALREADY_INITIALIZED});
    }

    Account account = new Account(accountRef.isActiveCard(), accountRef.getAvailableLimit());
    accountRepository.save(account);

    return new OutputAccount(new AccountRef(account.isActiveCard(), account.getAvailableLimit()));
  }
}
