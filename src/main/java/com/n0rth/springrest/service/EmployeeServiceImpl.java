package com.n0rth.springrest.service;

import com.n0rth.springrest.dao.EmployeeDao;
import com.n0rth.springrest.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeDao.getAll();
    }

    @Override
    @Transactional
    public Optional<Employee> getEmployee(long id) {
        return employeeDao.get(id);
    }

    @Override
    @Transactional
    public void saveEmployee(Employee employee) {
        long id = employee.getId();
        if (id == 0) {
            employeeDao.save(employee);
        } else {
            employeeDao.update(employee);
        }
    }


    @Override
    @Transactional
    public void deleteEmployee(long id) {
        employeeDao.delete(id);
    }

    @Override
    @Transactional
    public void updateEmployee(Employee employee) {
        employeeDao.update(employee);
    }
}
