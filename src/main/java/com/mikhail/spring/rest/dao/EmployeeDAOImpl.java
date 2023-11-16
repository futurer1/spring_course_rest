package com.mikhail.spring.rest.dao;

import com.mikhail.spring.rest.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Employee> getAllEmployees() {

        Session session = sessionFactory.getCurrentSession();
//        List<Employee> allEmployees = session.createQuery("from Employee", Employee.class)
//                .getResultList();

        Query<Employee> query = session.createQuery("from Employee", Employee.class);
        List<Employee> allEmployees = query.getResultList();

        return allEmployees;
    }

    @Override
    public void saveEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();

        // при совпадении primary key будет происходить update
        session.saveOrUpdate(employee);
    }

    @Override
    public void delEmployee(int empId) {
        Session session = sessionFactory.getCurrentSession();

        Query<Employee> query = session.createQuery("delete from Employee where id =:employeeId");
        // bind значения на метку
        query.setParameter("employeeId", empId);
        query.executeUpdate();

        //Employee employee = session.get(Employee.class, empId);
        //session.delete(employee);
    }

    @Override
    public Employee getEmployee(int empId) {
        Session session = sessionFactory.getCurrentSession();

        Employee employee = session.get(Employee.class, empId);
        return  employee;
    }

}