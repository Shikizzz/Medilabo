package com.medilabo.front.controller;

import com.medilabo.front.model.PatientDTO;
import com.medilabo.front.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.HTML;


@Controller
public class LoginController {


    @GetMapping("/login")
    public String getLogin(HttpServletRequest request, HttpSession session) {
        return "login.html";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute(name = "user") User user, BindingResult result, Model model, HttpSession session, HttpServletResponse response) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<User> request = new HttpEntity<User>(user, headers);
        restTemplate.postForEntity("http://localhost:8080/login", request, HTML.class);
        return "redirect:patients";
    }


    @GetMapping("/error")
    public String getError() {
        return "error.html";
    }
}
