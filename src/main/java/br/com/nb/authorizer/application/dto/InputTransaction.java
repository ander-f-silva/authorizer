package br.com.nb.authorizer.application.dto;

import com.google.gson.annotations.SerializedName;

public class InputTransaction {
  @SerializedName("transaction")
  private TransactionRef transaction;

  public InputTransaction() {}

  public InputTransaction(TransactionRef transaction) {
    this.transaction = transaction;
  }

  public TransactionRef getTransaction() {
    return transaction;
  }
}
