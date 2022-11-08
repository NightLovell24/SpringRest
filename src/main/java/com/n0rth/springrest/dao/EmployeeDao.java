package com.n0rth.springrest.dao;

import com.n0rth.springrest.entity.Employee;
import com.n0rth.springrest.exceptionhandling.exception.NoSuchEmployeeException;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {

    Optional<Employee> get(long id);

    List<Employee> getAll();

    void save(Employee employee);

    void delete(long id) throws NoSuchEmployeeException;

    void update(Employee employee);



}
