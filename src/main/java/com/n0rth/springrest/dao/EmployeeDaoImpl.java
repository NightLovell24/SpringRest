package com.n0rth.springrest.dao;

import com.n0rth.springrest.entity.Employee;
import com.n0rth.springrest.exceptionhandling.exception.NoSuchEmployeeException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {


    private final SessionFactory sessionFactory;

    @Autowired
    public EmployeeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Employee> get(long id) {
        Session session = sessionFactory.getCurrentSession();

        Employee employee = session.get(Employee.class, id);

        return Optional.ofNullable(employee);
    }

    @Override
    public List<Employee> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<Employee> query = session.createQuery("from Employee ", Employee.class);
        List<Employee> sortedEmployees = query.getResultList().stream()
                .sorted(Comparator.comparingLong(Employee::getId)).collect(Collectors.toList());
        return sortedEmployees;
    }

    @Override
    public void save(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.save(employee);
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        Employee employee = get(id).orElseThrow(() ->
        {
            throw new NoSuchEmployeeException("There is no employee with id=" + id);
        });
        session.delete(employee);
    }

    @Override
    public void update(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.update(employee);
    }
}
