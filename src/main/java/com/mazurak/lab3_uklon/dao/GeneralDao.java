package com.mazurak.lab3_uklon.dao;

import java.util.List;
import java.util.Optional;

import java.util.List;
import java.util.Optional;

public interface GeneralDao<T, ID> {
    List<T> findAll();

    Optional<T> findById(ID id);

    int create(T entity);

    int update(ID id, T entity);

    int delete(ID id);
}