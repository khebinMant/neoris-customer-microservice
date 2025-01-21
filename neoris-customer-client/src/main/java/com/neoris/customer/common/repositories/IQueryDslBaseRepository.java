package com.neoris.customer.common.repositories;

public interface IQueryDslBaseRepository<T> {
    void save(T entity);
    void update(T entity);
}
