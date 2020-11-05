package br.com.nb.authorizer.infrastructure;

import br.com.nb.authorizer.domain.repository.Repository;

public class StorageInMemoryConfig {

  private static Repository instance;

  public static Repository<?> getFactory() {
    if (instance == null) {
      instance = new StorageInMemory<>();
    }

    return instance;
  }
}
