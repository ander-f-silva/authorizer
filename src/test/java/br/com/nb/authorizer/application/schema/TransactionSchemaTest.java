package br.com.nb.authorizer.application.schema;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TransactionSchemaTest {
  @Test
  @DisplayName("should valid the json transaction is true")
  public void tesTransactionSchemaValid() {
    assertTrue(
        new TransactionSchema()
            .valid(
                "{ \"transaction\": { \"merchant\": \"Burger King\", \"amount\": 20, \"time\":\"2019-02-13T10:00:00.000Z\" } }\n"));
  }

  @Test
  @DisplayName("should valid the json transaction is false")
  public void tesTransactionSchemaInValid() {
    assertFalse(
        new TransactionSchema()
            .valid(
                "{ \"transaction\": { \"\": \"Burger King\", \"amount\": 20, \"create_at\":\"2019-02-13T10:00:00.000Z\" } }\n"));
  }
}
