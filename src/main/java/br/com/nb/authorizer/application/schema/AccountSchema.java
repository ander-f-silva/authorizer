package br.com.nb.authorizer.application.schema;

import java.util.HashSet;

public class AccountSchema extends OperationSchema {
  public AccountSchema() {
    fields = new HashSet<>(3);
    fields.add("account");
    fields.add("activeCard");
    fields.add("availableLimit");
  }
}
