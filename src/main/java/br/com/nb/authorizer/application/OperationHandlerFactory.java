package br.com.nb.authorizer.application;

import br.com.nb.authorizer.application.dto.InputTransaction;
import br.com.nb.authorizer.application.dto.OutputAccount;
import br.com.nb.authorizer.domain.AccountCreation;
import br.com.nb.authorizer.domain.AuthorizationTransaction;
import br.com.nb.authorizer.domain.repository.Repository;
import br.com.nb.authorizer.infrastructure.StorageInMemoryConfig;
import br.com.nb.authorizer.application.wrapper.JsonMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static br.com.nb.authorizer.application.OperationType.ACCOUNT_CREATION;
import static br.com.nb.authorizer.application.OperationType.AUTHORIZATION_TRANSACTION;

@SuppressWarnings("rawtypes")
public class OperationHandlerFactory {
  private final Map<OperationType, OperationHandler> operationsHandlers;
  private final JsonMapper<InputTransaction> inputJsonMapper;
  private final JsonMapper<OutputAccount> outputJsonMapper;

  public OperationHandlerFactory() {
    Repository repository = StorageInMemoryConfig.getFactory();

    inputJsonMapper = new JsonMapper<>();
    outputJsonMapper = new JsonMapper<>();
    operationsHandlers = new HashMap<>();

    operationsHandlers.put(
        AUTHORIZATION_TRANSACTION,
        new AuthorizationTransactionHandler(
            new AuthorizationTransaction(repository), inputJsonMapper, outputJsonMapper));
    operationsHandlers.put(
        ACCOUNT_CREATION,
        new AccountCreationHandler(
            new AccountCreation(repository), inputJsonMapper, outputJsonMapper));
  }

  public Optional<OperationHandler> getOperationHandler(OperationType operationType) {
    return Optional.ofNullable(operationsHandlers.get(operationType));
  }
}
