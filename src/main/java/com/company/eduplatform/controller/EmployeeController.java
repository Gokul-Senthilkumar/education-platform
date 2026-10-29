
package com.company.eduplatform.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.eduplatform.model.Employee;
import com.company.eduplatform.service.EmployeeService;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;
    private final PasswordEncoder passwordEncoder;

    public EmployeeController(
            EmployeeService employeeService,
            PasswordEncoder passwordEncoder) {
        this.employeeService = employeeService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegistrationPage() {
        return "register";
    }

    @GetMapping("/login")
public String showLoginPage() {
    return "login";
}

    @PostMapping("/register")
    public String registerEmployee(
            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam String password,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (fullName.isBlank() || email.isBlank() || password.isBlank()) {
            model.addAttribute("error", "All fields are required.");
            return "register";
        }

        if (employeeService.findByEmail(email.trim()).isPresent()) {
            model.addAttribute("error", "This email is already registered.");
            return "register";
        }

        Employee employee = new Employee();
        employee.setFullName(fullName.trim());
        employee.setEmail(email.trim());
        employee.setPassword(passwordEncoder.encode(password));

        employeeService.registerEmployee(employee);

        redirectAttributes.addFlashAttribute(
                "success", "Registration successful. Please log in.");

        return "redirect:/login";
    }
}
