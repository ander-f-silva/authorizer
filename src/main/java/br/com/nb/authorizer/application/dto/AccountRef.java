package br.com.nb.authorizer.application.dto;

import java.util.Objects;

public class AccountRef {
  private boolean activeCard;
  private long availableLimit;

  public AccountRef() {}

  public AccountRef(boolean activeCard, long availableLimit) {
    this.activeCard = activeCard;
    this.availableLimit = availableLimit;
  }

  public boolean isActiveCard() {
    return activeCard;
  }

  public long getAvailableLimit() {
    return availableLimit;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AccountRef that = (AccountRef) o;
    return activeCard == that.activeCard && availableLimit == that.availableLimit;
  }

  @Override
  public int hashCode() {
    return Objects.hash(activeCard, availableLimit);
  }
}
