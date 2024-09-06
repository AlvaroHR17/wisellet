package com.alvarohdr.gastosapi.domain.dao;

import com.alvarohdr.framework.dao.BaseDao;
import com.alvarohdr.gastosapi.domain.model.User;

import java.util.Optional;

public interface UserDao extends BaseDao<User> {

    Optional<User> findByEmail(String email);
}
