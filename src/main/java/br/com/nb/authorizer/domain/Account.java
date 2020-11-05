package br.com.nb.authorizer.domain;

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

  public boolean hasSufficientLimit(long amount) {
    return activeCard && (availableLimit - amount) < 0;
  }

  public boolean hasHighFrequencySmallInterval() {
    // TODO: I need do implement
    return false;
  }

  public boolean hasDoubledTransaction() {
    // TODO: I need do implement
    return false;
  }
}
