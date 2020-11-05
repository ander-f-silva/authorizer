package br.com.nb.authorizer.application;

import java.util.ArrayList;
import java.util.List;

public class OperationsExecutor {
  private final OperationHandlerFactory operationHandlerFactory;

  public OperationsExecutor() {
    operationHandlerFactory = new OperationHandlerFactory();
  }

  public List<String> execute(List<String> operations) {
    List<String> resultsOperations = new ArrayList<>();

    for (String operation : operations) {
      var operationType = OperationType.get(operation);
      var optionalOperationHandler = operationHandlerFactory.getOperationHandler(operationType);

      if (optionalOperationHandler.isPresent()) {
        var operationHandler = optionalOperationHandler.get();
        var resultOperation = operationHandler.execution(operation);
        resultsOperations.add(resultOperation);
      }
    }

    return resultsOperations;
  }
}
