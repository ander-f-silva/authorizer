package br.com.nb.authorizer.application;

import br.com.nb.authorizer.application.dto.InputAccount;
import br.com.nb.authorizer.application.dto.OutputAccount;
import br.com.nb.authorizer.domain.AccountCreation;
import br.com.nb.authorizer.application.wrapper.JsonMapper;

public class AccountCreationHandler implements OperationHandler {
  private final AccountCreation accountCreation;
  private final JsonMapper<InputAccount> inputJsonMapper;
  private final JsonMapper<OutputAccount> outputJsonMapper;

  public AccountCreationHandler(
      AccountCreation accountCreation, JsonMapper inputJsonMapper, JsonMapper outputJsonMapper) {
    this.accountCreation = accountCreation;
    this.inputJsonMapper = inputJsonMapper;
    this.outputJsonMapper = outputJsonMapper;
  }

  @Override
  public String execution(String json) {
    var inputAccount = inputJsonMapper.mapperJsonToObject(json, InputAccount.class);
    var outputAccount = accountCreation.create(inputAccount);
    return outputJsonMapper.mapperObjectToJson(outputAccount);
  }
}
