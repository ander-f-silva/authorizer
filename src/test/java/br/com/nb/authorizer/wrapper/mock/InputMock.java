package br.com.nb.authorizer.wrapper.mock;

import java.util.Objects;

public class InputMock {
  private String name;
  private int age;

  public InputMock(String name, int age) {
    this.name = name;
    this.age = age;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    InputMock inputMock = (InputMock) o;
    return age == inputMock.age && name.equals(inputMock.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, age);
  }
}
