package br.com.nb.authorizer.domain;

import java.util.Date;

class Transaction {
  private String merchant;
  private long amount;
  private Date time;

  public Transaction(String merchant, long amount, Date time) {
    this.merchant = merchant;
    this.amount = amount;
    this.time = time;
  }

  public long getAmount() {
    return amount;
  }
}
