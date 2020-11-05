package br.com.nb.authorizer.application.dto;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class InputAccount {
  @SerializedName("account")
  private AccountRef accountRef;

  public InputAccount() {
    this.accountRef = new AccountRef();
  }

  public InputAccount(AccountRef accountRef) {
    this.accountRef = accountRef;
  }

  public AccountRef getAccountRef() {
    return accountRef;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    InputAccount that = (InputAccount) o;
    return Objects.equals(accountRef, that.accountRef);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountRef);
  }
}
