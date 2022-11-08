package com.n0rth.springrest.controller;

import com.n0rth.springrest.entity.Employee;
import com.n0rth.springrest.exceptionhandling.exception.NoSuchEmployeeException;
import com.n0rth.springrest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AppRestController {

    private final EmployeeService employeeService;

    @Autowired
    public AppRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        List<Employee> employeeList = employeeService.getAllEmployees();


        return employeeList;
    }

    @GetMapping("/employees/{id}")
    public Employee showEmployee(@PathVariable long id) {
        Employee employee = employeeService.getEmployee(id).orElseThrow(() -> {
            throw new NoSuchEmployeeException("There is no employee with id=" + id);
        });
        return employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {

        employeeService.saveEmployee(employee);

        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.updateEmployee(employee);

        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable long id) {
       employeeService.deleteEmployee(id);

       return "Employee with " + id + " was deleted";
    }
}
