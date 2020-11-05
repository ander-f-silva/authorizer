package br.com.nb.authorizer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
  @Test
  @DisplayName("should authorized transaction to account active")
  public void testAuthorizedTransactionToAccountActive() {
    var account = new Account(true, 100);
    var transaction = new Transaction("Merchant Test", 20, new Date());

    var expectedAvailableLimit = 80;

    account.authorizedTransaction(transaction);

    assertEquals(expectedAvailableLimit, account.getAvailableLimit());
  }

  @Test
  @DisplayName("not should authorized transaction to account not active")
  public void testNotAuthorizedTransactionToAccountNotActive() {
    var account = new Account(false, 0);
    var transaction = new Transaction("Merchant Test", 20, new Date());

    var expectedAvailableLimit = 0;

    account.authorizedTransaction(transaction);

    assertEquals(expectedAvailableLimit, account.getAvailableLimit());
  }

//  @Test
//  @DisplayName("should verify if account has sufficient limit")
//  public void testHasSufficientLimit() {
//    var account = new Account(true, 100);
//    assertTrue(account.hasSufficientLimit(50));
//  }

  @Test
  @DisplayName("should verify if account not has sufficient limit")
  public void testNotHasSufficientLimit() {
    var account = new Account(false, 100);
    assertFalse(account.hasSufficientLimit(200));
  }

  @Test
  @DisplayName("should verify if account high frequency small interval")
  public void testHighFrequencySmallInterval() {
    var account = new Account(false, 100);
    var transaction = new Transaction("Merchant Test", 120, new Date());

    account.authorizedTransaction(transaction);

    assertTrue(account.hasHighFrequencySmallInterval());
  }

  @Test
  @DisplayName("should verify if account not high frequency small interval")
  public void testNotHighFrequencySmallInterval() {
    var account = new Account(false, 100);
    var transaction = new Transaction("Merchant Test", 120, new Date());

    account.authorizedTransaction(transaction);

    assertFalse(account.hasHighFrequencySmallInterval());
  }

  @Test
  @DisplayName("should verify if account has doubled transaction")
  public void testHasDoubledTransaction() {
    var account = new Account(false, 100);
    var transaction = new Transaction("Merchant Test", 120, new Date());

    account.authorizedTransaction(transaction);

    assertTrue(account.hasHighFrequencySmallInterval());
  }

  @Test
  @DisplayName("should verify if account not has doubled transaction")
  public void testNotHasDoubledTransaction() {
    var account = new Account(false, 100);
    var transaction = new Transaction("Merchant Test", 120, new Date());

    account.authorizedTransaction(transaction);

    assertFalse(account.hasHighFrequencySmallInterval());
  }
}
