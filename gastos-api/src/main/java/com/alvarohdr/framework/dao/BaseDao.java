package com.alvarohdr.framework.dao;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T> {
    void saveOrUpdate(T obj);
    List<T> findAll();
    List<T> findAllSecure(long userId);
    Optional<T> get(long id);
    Optional<T> getSecure(long id, long userId);
    void delete(T obj);
}
