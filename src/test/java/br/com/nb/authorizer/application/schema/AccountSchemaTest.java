package br.com.nb.authorizer.application.schema;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountSchemaTest {
  @Test
  @DisplayName("should valid the json account is true")
  public void testAccountSchemaValid() {
    assertTrue(
        new AccountSchema()
            .valid("{ \"account\": { \"activeCard\": true, \"availableLimit\": 100 } }"));
  }

  @Test
  @DisplayName("should valid the json account is false")
  public void testAccountSchemaInValid() {
    assertFalse(
        new AccountSchema().valid("{ \"\": { \"activeCard\": true, \"available\": 100 } }"));
  }
}
