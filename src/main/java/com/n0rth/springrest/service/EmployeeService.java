package com.n0rth.springrest.service;

import com.n0rth.springrest.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Optional<Employee> getEmployee(long id);

    void saveEmployee(Employee employee);

    void deleteEmployee(long id);

    void updateEmployee(Employee employee);
}
