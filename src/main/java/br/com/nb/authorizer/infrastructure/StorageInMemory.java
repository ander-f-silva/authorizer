package br.com.nb.authorizer.infrastructure;

import br.com.nb.authorizer.domain.repository.Repository;

import java.util.HashSet;
import java.util.Set;

class StorageInMemory<T> implements Repository<T> {
  private Set<T> elements = new HashSet();

  public StorageInMemory(Set<T> elements) {
    this.elements = elements;
  }

  public StorageInMemory() {}

  public T save(T value) {
    elements.add(value);
    return elements.iterator().next();
  }

  public boolean exist() {
    return !elements.isEmpty();
  }

  public T findOne() {
    return elements.stream().findFirst().orElse(null);
  }
}
