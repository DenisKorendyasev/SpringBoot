package com.example.spring.services;

import java.util.Collection;
public interface CRUDService<T> {
    T getById(Integer id);
    Collection<T> getAll();
    T create(T item);
    T update(Integer id, T item);
    void delete(Integer id);
}
