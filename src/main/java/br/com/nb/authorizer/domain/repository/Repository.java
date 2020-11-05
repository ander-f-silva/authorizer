package br.com.nb.authorizer.domain.repository;

public interface Repository<T> {
  T save(T value);

  boolean exist();

  T findOne();
}
