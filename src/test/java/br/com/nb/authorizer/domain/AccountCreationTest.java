package br.com.nb.authorizer.domain;

import br.com.nb.authorizer.application.dto.AccountRef;
import br.com.nb.authorizer.application.dto.InputAccount;
import br.com.nb.authorizer.application.dto.OutputAccount;
import br.com.nb.authorizer.domain.repository.Repository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountCreationTest {
  @Test
  @DisplayName("should create a account active")
  public void testAccountCreationActive() {
    Repository<Account> mockRepository = createRepositoryMock(new ArrayList<>());

    var accountCreation = new AccountCreation(mockRepository);
    var outputAccount = accountCreation.create(new InputAccount(new AccountRef(true, 100)));

    var expected = new OutputAccount(new AccountRef(true, 100), new String[] {});

    assertEquals(expected, outputAccount);
  }

  @Test
  @DisplayName("should create a account not active")
  public void testAccountCreationNotActive() {
    Repository<Account> mockRepository = createRepositoryMock(new ArrayList<>());

    var accountCreation = new AccountCreation(mockRepository);
    var outputAccount = accountCreation.create(new InputAccount(new AccountRef(false, 340)));

    var expected = new OutputAccount(new AccountRef(false, 340), new String[] {});

    assertEquals(expected, outputAccount);
  }

  @Test
  @DisplayName("should not take into account when there is an account already registered")
  public void testNotAccountCreation() {
    Repository<Account> mockRepository =
        createRepositoryMock(Arrays.asList(new Account(true, 300)));

    var accountCreation = new AccountCreation(mockRepository);
    var outputAccount = accountCreation.create(new InputAccount(new AccountRef(true, 780)));

    var expected =
        new OutputAccount(new AccountRef(true, 300), new String[] {"account-already-initialized"});

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
          public Account save(Account account) {
            mockAccounts.add(account);
            return account;
          }

          @Override
          public boolean exist() {
            return !mockAccounts.isEmpty();
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
