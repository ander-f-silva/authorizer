package br.com.nb.authorizer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.util.Calendar;
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

  @Test
  @DisplayName("should verify if account has sufficient limit")
  public void testHasSufficientLimit() {
    var account = new Account(true, 100);
    assertFalse(account.hasNotSufficientLimit(50));
  }

  @Test
  @DisplayName("should verify if account not has sufficient limit")
  public void testNotHasSufficientLimit() {
    var account = new Account(true, 100);
    assertTrue(account.hasNotSufficientLimit(200));
  }

  @Test
  @DisplayName("should verify if account high frequency small interval")
  public void testHighFrequencySmallInterval() {
    var account = new Account(true, 100);

    var now = new Date();

    var penultimateTransaction = new Transaction("Penultimate Merchant", 10, now);
    account.authorizedTransaction(penultimateTransaction);

    var lastedTransaction = new Transaction("Lasted Merchant", 20, now);
    account.authorizedTransaction(lastedTransaction);

    var currentTransaction = new Transaction("Current Merchant", 30, now);

    assertTrue(account.hasHighFrequencySmallInterval(currentTransaction));
  }

  @Test
  @DisplayName("should verify if account not high frequency small interval")
  public void testNotHighFrequencySmallInterval() {
    var account = new Account(true, 100);

    var penultimateDate = new Date()
            .toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()
            .minusMinutes(1);

    var latestDate = new Date()
            .toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()
            .minusMinutes(2);

    var currentDate =  new Date();

    var penultimateTransaction = new Transaction("Penultimate Merchant", 10, Date.from(penultimateDate.atZone(ZoneId.systemDefault()).toInstant()));
    account.authorizedTransaction(penultimateTransaction);

    var lastedTransaction = new Transaction("Lasted Merchant", 20, Date.from(latestDate.atZone(ZoneId.systemDefault()).toInstant()));
    account.authorizedTransaction(lastedTransaction);

    var currentTransaction = new Transaction("Current Merchant", 30, currentDate);

    assertFalse(account.hasHighFrequencySmallInterval(currentTransaction));
  }

  @Test
  @DisplayName("should verify if account has doubled transaction")
  public void testHasDoubledTransaction() {
    var account = new Account(true, 100);

    var now = new Date();

    var lastedTransaction = new Transaction("Merchant Test", 20, now);
    account.authorizedTransaction(lastedTransaction);

    var currentTransaction = new Transaction("Merchant Test", 20, now);

    assertTrue(account.hasDoubledTransaction(currentTransaction));
  }

  @Test
  @DisplayName("should verify if account not has doubled transaction")
  public void testNotHasDoubledTransaction() {
    var account = new Account(true, 100);

    var latestDate = new Date()
            .toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()
            .minusMinutes(2);

    var now = new Date();

    var lastedTransaction = new Transaction("Merchant Test", 20, Date.from(latestDate.atZone(ZoneId.systemDefault()).toInstant()));
    account.authorizedTransaction(lastedTransaction);

    var currentTransaction = new Transaction("Merchant Test", 20, now);

    assertFalse(account.hasDoubledTransaction(currentTransaction));
  }
}
