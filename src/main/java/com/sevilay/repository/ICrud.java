package com.sevilay.repository;

import com.sevilay.repository.entity.Futbolcu;

import java.util.List;
import java.util.Optional;

public interface ICrud<T> {

    void save(T t);

    void update(T t);

    void delete(Long id);
    List<T> findAll();

    Optional<Futbolcu> findById(Long id);


}
