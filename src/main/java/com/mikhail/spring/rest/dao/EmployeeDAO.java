package com.mikhail.spring.rest.dao;

import com.mikhail.spring.rest.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    public List<Employee> getAllEmployees();

    public void saveEmployee(Employee employee);

    public void delEmployee(int empId);

    public Employee getEmployee(int empId);
}