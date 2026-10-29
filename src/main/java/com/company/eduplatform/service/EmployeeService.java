
package com.company.eduplatform.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.company.eduplatform.model.Employee;
import com.company.eduplatform.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee registerEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional<Employee> findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }
}
