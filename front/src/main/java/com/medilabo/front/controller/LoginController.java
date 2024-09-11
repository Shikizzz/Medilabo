package com.medilabo.front.controller;

import com.medilabo.front.model.LoginForm;
import com.medilabo.front.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/auth")
public class LoginController {
    private LoginService service;

    public LoginController(LoginService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login.html";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute(name = "loginForm") LoginForm loginForm, HttpSession session, BindingResult result) {
        if (result.hasErrors()) {
            return "error";
        }
        session.setAttribute("token", service.postLogin(loginForm));
        String test = "abc";
        return "patients.html";
    }
}