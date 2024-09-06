package com.alvarohdr.framework.dao;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T> {
    void saveOrUpdate(T obj);
    List<T> findAll();
    Optional<T> get(long id);
    void delete(T obj);
}
