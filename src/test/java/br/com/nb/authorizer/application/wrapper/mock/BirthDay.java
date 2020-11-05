package br.com.nb.authorizer.application.wrapper.mock;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class BirthDay {
  public static Date createDate() {
    LocalDateTime localDateTime = LocalDateTime.of(2019, 2, 13, 10, 0);
    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
  }
}
