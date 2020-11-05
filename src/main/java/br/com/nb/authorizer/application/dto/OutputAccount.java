package br.com.nb.authorizer.application.dto;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.Objects;

public class OutputAccount {
  @SerializedName("account")
  private AccountRef accountRef;

  private String[] violations;

  public OutputAccount(AccountRef accountRef, String[] violations) {
    this.accountRef = accountRef;
    this.violations = violations;
  }

  public OutputAccount(AccountRef accountRef) {
    this.accountRef = accountRef;
    this.violations = new String[] {};
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OutputAccount that = (OutputAccount) o;
    return Objects.equals(accountRef, that.accountRef)
        && Arrays.equals(violations, that.violations);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(accountRef);
    result = 31 * result + Arrays.hashCode(violations);
    return result;
  }
}
