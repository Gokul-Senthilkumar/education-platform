package com.company.eduplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.company.eduplatform.model.ApplicationSecret;
import com.company.eduplatform.service.SecretsManagerService;

@Controller
public class HomeController {

    private final SecretsManagerService secretsManagerService;

    public HomeController(SecretsManagerService secretsManagerService) {
        this.secretsManagerService = secretsManagerService;
    }

    @GetMapping("/")
    public String home(Model model) {

        ApplicationSecret secret = secretsManagerService.getSecret();

        model.addAttribute("smtpUsername", secret.getSmtpUsername());
        model.addAttribute("jwtSecret", secret.getJwtSecret());

        return "home";
    }

}