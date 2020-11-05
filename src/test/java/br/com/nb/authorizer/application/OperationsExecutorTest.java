package br.com.nb.authorizer.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OperationsExecutorTest {
  @Test
  @DisplayName("should execute operation information the operations")
  public void testExecuteOperation() {
    var operations =
        Arrays.asList(
            "{ \"account\": { \"activeCard\": true, \"availableLimit\": 100 } }",
            " { \"account\": { \"activeCard\": true, \"availableLimit\": 350 } }",
            " { \"transaction\": { \"merchant\": \"Burger King\", \"amount\": 20, \"time\":\"2019-02-13T10:00:00.000Z\" } }",
            "{ \"transaction\": { \"merchant\": \"Habbib's\", \"amount\": 90, \"time\":\"2019-02-13T11:00:00.000Z\" } }");

    operations = new OperationsExecutor().execute(operations);

    var expected =
        Arrays.asList(
            "{\"account\":{\"activeCard\":true,\"availableLimit\":100},\"violations\":[]}",
            "{\"account\":{\"activeCard\":true,\"availableLimit\":100},\"violations\":[\"account-already-initialized\"]}",
            "{\"account\":{\"activeCard\":true,\"availableLimit\":80},\"violations\":[]}",
            "{\"account\":{\"activeCard\":true,\"availableLimit\":80},\"violations\":[\"insufficient-limit\"]}");

    assertEquals(expected, operations);
  }

  @Test
  @DisplayName("should return empty when not informed the operations")
  public void testExecuteOperationAndReturnListEmpty() {
    var operations = new OperationsExecutor().execute(new ArrayList<>());
    assertTrue(operations.isEmpty());
  }
}
