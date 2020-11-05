package br.com.nb.authorizer.application;

import br.com.nb.authorizer.application.dto.InputTransaction;
import br.com.nb.authorizer.application.dto.OutputAccount;
import br.com.nb.authorizer.domain.AuthorizationTransaction;
import br.com.nb.authorizer.wrapper.JsonMapper;

public class AuthorizationTransactionHandler implements OperationHandler {
  private final AuthorizationTransaction authorizationTransaction;
  private final JsonMapper<InputTransaction> inputJsonMapper;
  private final JsonMapper<OutputAccount> outputJsonMapper;

  public AuthorizationTransactionHandler(
      AuthorizationTransaction authorizationTransaction,
      JsonMapper inputJsonMapper,
      JsonMapper outputJsonMapper) {
    this.authorizationTransaction = authorizationTransaction;
    this.inputJsonMapper = inputJsonMapper;
    this.outputJsonMapper = outputJsonMapper;
  }

  @Override
  public String execution(String json) {
    var inputTransaction = inputJsonMapper.mapperJsonToObject(json, InputTransaction.class);
    var outputTransaction = authorizationTransaction.authorize(inputTransaction);
    return outputJsonMapper.mapperObjectToJson(outputTransaction);
  }
}
