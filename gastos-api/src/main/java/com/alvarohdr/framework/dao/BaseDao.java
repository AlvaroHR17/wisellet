package com.alvarohdr.framework.dao;

import java.util.List;

public interface BaseDao<T> {
    void saveOrUpdate(T obj);
    List<T> findAll();
    void delete(T obj);
}
