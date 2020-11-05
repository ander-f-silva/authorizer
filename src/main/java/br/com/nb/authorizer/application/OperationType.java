package br.com.nb.authorizer.application;

import br.com.nb.authorizer.application.schema.AccountSchema;
import br.com.nb.authorizer.application.schema.OperationSchema;
import br.com.nb.authorizer.application.schema.TransactionSchema;

public enum OperationType {
  AUTHORIZATION_TRANSACTION(new TransactionSchema()),
  ACCOUNT_CREATION(new AccountSchema());

  private OperationSchema operationSchema;

  OperationType(OperationSchema operationSchema) {
    this.operationSchema = operationSchema;
  }

  public static OperationType get(String operation) {
    for (var operationType : values()) {
      if (operationType.operationSchema.valid(operation)) return operationType;
    }
    return null;
  }
}
