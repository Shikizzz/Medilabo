package com.medilabo.front.service;

import com.medilabo.front.model.LoginForm;
import com.medilabo.front.repository.LoginRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private LoginRepository repository;

    public LoginService(LoginRepository repository) {
        this.repository = repository;
    }


    public String postLogin(LoginForm form){
        return repository.postLogin(form);
    }
}
