package com.mikhail.spring.rest.controller;

import com.mikhail.spring.rest.entity.Employee;
import com.mikhail.spring.rest.exception_handling.EmployeeIncorrectData;
import com.mikhail.spring.rest.exception_handling.NoSuchEmployeeException;
import com.mikhail.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
    }

    @GetMapping("/employees/{id}")
    public Employee showEmployee(@PathVariable int id) {
        Employee emp = employeeService.getEmployee(id);

        if (emp == null) {
            throw new NoSuchEmployeeException("Employee is not find with ID = " + id);
        }

        return emp;
    }

}
