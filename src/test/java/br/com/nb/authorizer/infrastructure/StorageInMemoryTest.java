package br.com.nb.authorizer.infrastructure;

import br.com.nb.authorizer.infrastructure.mock.ElementMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StorageInMemoryTest {
  @Test
  @DisplayName("should save a element")
  public void testSaveElement() {
    var element = new ElementMock("Cobby");
    var storage = new StorageInMemory<ElementMock>();

    storage.save(element);
    var resultElement = storage.findOne();

    assertNotNull(resultElement);
    assertEquals(element, resultElement);
  }

  @Test
  @DisplayName("should informed that element not exist")
  public void testReturnElementNotExist() {
    var storage = new StorageInMemory<ElementMock>();
    assertFalse(storage.exist());
  }

  @Test
  @DisplayName("should informed that element exist")
  public void testReturnElementExist() {
    var storage = new StorageInMemory<>(Set.of(new ElementMock("Cobby")));
    assertTrue(storage.exist());
  }

  @Test
  @DisplayName("should return a element save")
  public void testReturnOneElement() {
    var element = new ElementMock("Cobby");
    var storage = new StorageInMemory<>(Set.of(new ElementMock("Cobby")));

    var resultElement = storage.findOne();

    assertNotNull(resultElement);
    assertEquals(element, resultElement);
  }

  @Test
  @DisplayName("should return null to element not found")
  public void testReturnNullToNotFound() {
    var storage = new StorageInMemory<>();
    assertNull(storage.findOne());
  }
}
