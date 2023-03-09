package com.azgurski.repository;

import java.util.List;

public interface CRUDRepository<K,T> {

    T findOne(K id);

    List<T> findAll();

    T create(T object);

    T update(T object);

    K delete(K id);
}