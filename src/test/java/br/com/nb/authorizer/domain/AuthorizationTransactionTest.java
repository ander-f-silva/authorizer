package br.com.nb.authorizer.domain;

import br.com.nb.authorizer.application.dto.AccountRef;
import br.com.nb.authorizer.application.dto.InputTransaction;
import br.com.nb.authorizer.application.dto.OutputAccount;
import br.com.nb.authorizer.application.dto.TransactionRef;
import br.com.nb.authorizer.domain.repository.Repository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorizationTransactionTest {
  @Test
  @DisplayName("should authorized transaction to a account")
  public void testAuthorizedTransactionToAccount() {
    Repository<Account> mockRepository =
        createRepositoryMock(Arrays.asList(new Account(true, 100)));

    var authorizationTransaction = new AuthorizationTransaction(mockRepository);
    var outputAccount =
        authorizationTransaction.authorize(
            new InputTransaction(new TransactionRef("Merchant Test", 20, new Date())));

    var expected = new OutputAccount(new AccountRef(true, 80), new String[] {});

    assertEquals(expected, outputAccount);
  }

  @Test
  @DisplayName("not should authorized transaction to card not active")
  public void testNotAuthorizedTransactionToCardNotActive() {
    Repository<Account> mockRepository = createRepositoryMock(Arrays.asList(new Account(false, 0)));

    var authorizationTransaction = new AuthorizationTransaction(mockRepository);
    var outputAccount =
        authorizationTransaction.authorize(
            new InputTransaction(new TransactionRef("Merchant Test", 20, new Date())));

    var expected = new OutputAccount(new AccountRef(false, 0), new String[] {"card-not-active"});

    assertEquals(expected, outputAccount);
  }

  @Test
  @DisplayName("not should authorized transaction to insufficient limit")
  public void testNotAuthorizedTransactionToInsufficientLimit() {
    Repository<Account> mockRepository = createRepositoryMock(Arrays.asList(new Account(true, 20)));

    var authorizationTransaction = new AuthorizationTransaction(mockRepository);
    var outputAccount =
        authorizationTransaction.authorize(
            new InputTransaction(new TransactionRef("Merchant Test", 200, new Date())));

    var expected = new OutputAccount(new AccountRef(true, 20), new String[] {"insufficient-limit"});

    assertEquals(expected, outputAccount);
  }

  @Test
  @DisplayName("not should authorized transaction to high frequency small interval")
  public void testNotAuthorizedTransactionToHighFrequencySmallInterval() {
    Repository<Account> mockRepository = createRepositoryMock(Arrays.asList(new Account(true, 20)));

    var authorizationTransaction = new AuthorizationTransaction(mockRepository);
    var outputAccount =
        authorizationTransaction.authorize(
            new InputTransaction(new TransactionRef("Merchant Test", 200, new Date())));

    var expected =
        new OutputAccount(new AccountRef(true, 20), new String[] {"high-frequency-small-interval"});

    assertEquals(expected, outputAccount);
  }

  private Repository<Account> createRepositoryMock(List<Account> mockAccounts) {
    var mockRepository =
        new Repository<Account>() {
          private List<Account> mockAccounts = new ArrayList<>();

          public void setMockAccounts(List<Account> mockAccounts) {
            this.mockAccounts = mockAccounts;
          }

          @Override
          public Account save(Account value) {
            throw new UnsupportedOperationException();
          }

          @Override
          public boolean exist() {
            throw new UnsupportedOperationException();
          }

          @Override
          public Account findOne() {
            return mockAccounts.get(0);
          }
        };
    mockRepository.setMockAccounts(mockAccounts);
    return mockRepository;
  }
}
