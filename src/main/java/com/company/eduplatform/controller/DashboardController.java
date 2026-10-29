
package com.company.eduplatform.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.company.eduplatform.model.Course;
import com.company.eduplatform.model.Employee;
import com.company.eduplatform.repository.CourseRepository;
import com.company.eduplatform.repository.EmployeeRepository;

@Controller
public class DashboardController {

    private final EmployeeRepository employeeRepository;
    private final CourseRepository courseRepository;

    public DashboardController(
            EmployeeRepository employeeRepository,
            CourseRepository courseRepository) {
        this.employeeRepository = employeeRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {

        String email = authentication.getName();

        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalStateException("Employee account not found"));

        List<Course> courses = courseRepository.findAll();

        model.addAttribute("employee", employee);
        model.addAttribute("courses", courses);

        return "dashboard";
    }
}
