package br.com.nb.authorizer.application.schema;

import java.util.HashSet;

public class TransactionSchema extends OperationSchema {
  public TransactionSchema() {
    fields = new HashSet<>(4);
    fields.add("transaction");
    fields.add("merchant");
    fields.add("amount");
    fields.add("time");
  }
}
