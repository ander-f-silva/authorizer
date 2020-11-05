package br.com.nb.authorizer.infrastructure.mock;

import java.util.Objects;

public class ElementMock {
  private String name;

  public ElementMock(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ElementMock that = (ElementMock) o;
    return Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
