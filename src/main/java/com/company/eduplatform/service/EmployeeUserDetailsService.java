
package com.company.eduplatform.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.company.eduplatform.model.Employee;
import com.company.eduplatform.repository.EmployeeRepository;

@Service
public class EmployeeUserDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    public EmployeeUserDetailsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Employee employee = employeeRepository.findByEmail(email.trim())
                .orElseThrow(() ->
                        new UsernameNotFoundException("Employee not found"));

        return User.withUsername(employee.getEmail())
                .password(employee.getPassword())
                .roles("EMPLOYEE")
                .build();
    }
}
