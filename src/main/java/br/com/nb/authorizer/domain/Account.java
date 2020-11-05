package br.com.nb.authorizer.domain;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

class Account {
  private boolean activeCard;
  private long availableLimit;
  private List<Transaction> transactions;

  public Account(boolean activeCard, long availableLimit) {
    this.activeCard = activeCard;
    this.availableLimit = availableLimit;
    this.transactions = new ArrayList<>();
  }

  public boolean isActiveCard() {
    return activeCard;
  }

  public boolean isNotActiveCard() {
    return !activeCard;
  }

  public long getAvailableLimit() {
    return availableLimit;
  }

  public void authorizedTransaction(Transaction transaction) {
    if (activeCard) {
      transactions.add(transaction);
      var updateAmount = transactions.stream().mapToLong(t -> t.getAmount()).sum();
      availableLimit = availableLimit - updateAmount;
    }
  }

  public boolean hasNotSufficientLimit(long amount) {
    return activeCard && ((availableLimit - amount) < 0);
  }

  public boolean hasHighFrequencySmallInterval(Transaction currentTransaction) {
    if (activeCard && (transactions.size() >= 2)) {
      var penultimate = transactions.size() - 2;
      var latest = transactions.size() - 1;

      var penultimateTransaction = transactions.get(penultimate);
      var latestTransaction = transactions.get(latest);

      var penultimateTime =
          penultimateTransaction
              .getTime()
              .toInstant()
              .atZone(ZoneId.systemDefault())
              .toLocalDateTime();

      var lastTime =
          latestTransaction.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

      var currentTime =
          currentTransaction.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

      if ((currentTime.getYear() == lastTime.getYear()
              && currentTime.getYear() == penultimateTime.getYear())
          && (currentTime.getMonth().equals(lastTime.getMonth())
              && currentTime.getMonth().equals(penultimateTime.getMonth()))
          && (currentTime.getDayOfMonth() == lastTime.getDayOfMonth()
              && currentTime.getDayOfMonth() == penultimateTime.getDayOfMonth())
          && (currentTime.getHour() == lastTime.getHour()
              && currentTime.getHour() == penultimateTime.getHour())) {

        var intervalMinutes =
                (currentTime.getMinute() - penultimateTime.getMinute()) + (currentTime.getMinute() - lastTime.getMinute());

        if (intervalMinutes < 2) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean hasDoubledTransaction(Transaction currentTransaction) {
    if (activeCard && (!transactions.isEmpty())) {
      var latest = transactions.size() - 1;

      var latestTransaction = transactions.get(latest);

      var lastTime =
              latestTransaction.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

      var currentTime =
              currentTransaction.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

      if (currentTime.getYear() == lastTime.getYear()
              && currentTime.getMonth().equals(lastTime.getMonth())
              && currentTime.getDayOfMonth() == lastTime.getDayOfMonth()
              && currentTime.getHour() == lastTime.getHour()) {

        var intervalMinutes = currentTime.getMinute() - lastTime.getMinute();

        if (intervalMinutes < 2  && (currentTransaction.getMerchant().equals(latestTransaction.getMerchant()) && currentTransaction.getAmount() == latestTransaction.getAmount())) {
          return true;
        }
      }
    }
    return false;
  }
}
