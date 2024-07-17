package com.mitocode.service;

import com.mitocode.model.Medic;

import java.util.List;

public interface ICRUD<T, ID> {

    T save(T t);
    T update(ID id, T t);
    List<T> findAll();
    T findById(ID id);
    void delete(ID id);
}
