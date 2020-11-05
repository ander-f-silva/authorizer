package br.com.nb.authorizer.application.dto;

import java.util.Date;

public class TransactionRef {
  private String merchant;
  private long amount;
  private Date time;

  public TransactionRef() {}

  public TransactionRef(String merchant, long amount, Date time) {
    this.merchant = merchant;
    this.amount = amount;
    this.time = time;
  }

  public String getMerchant() {
    return merchant;
  }

  public long getAmount() {
    return amount;
  }

  public Date getTime() {
    return time;
  }
}
