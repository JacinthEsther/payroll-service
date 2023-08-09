package com.classwork.payroll.repository;

import com.classwork.payroll.model.Employee;

import java.util.List;
import java.util.Optional;

public interface RepositoryInterface<T> {

    T create(T obj);

    T update(T obj);

//    void delete(int id);

    Optional<T> findById(int id);

    List<T> findAll();


}
