package com.mikhail.spring.rest.controller;

import com.mikhail.spring.rest.entity.Employee;
import com.mikhail.spring.rest.exception_handling.EmployeeAlreadyExistsException;
import com.mikhail.spring.rest.exception_handling.NoSuchEmployeeException;
import com.mikhail.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {

        Employee emp = employeeService.getEmployee(employee.getId());
        if (emp != null) {
            throw new EmployeeAlreadyExistsException("Employee with ID = " + employee.getId() + " already added.");
        }

        employeeService.saveEmployee(employee);

        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);

        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String delEmployee(@PathVariable int id) {

        Employee emp = employeeService.getEmployee(id);

        if (emp == null) {
            throw new NoSuchEmployeeException("Employee is not find with ID = " + id);
        }

        employeeService.delEmployee(id);
        return "Employee ID = " + id + " was deleted.";
    }


}
