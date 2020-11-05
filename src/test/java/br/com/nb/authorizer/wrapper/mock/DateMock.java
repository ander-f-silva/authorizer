package br.com.nb.authorizer.wrapper.mock;

import java.util.Date;
import java.util.Objects;

public class DateMock {
  private Date birthDay;

  public DateMock(Date birthDay) {
    this.birthDay = birthDay;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DateMock that = (DateMock) o;
    return Objects.equals(birthDay, that.birthDay);
  }

  @Override
  public int hashCode() {
    return Objects.hash(birthDay);
  }
}
