package com.medilabo.front.service;

import com.medilabo.front.repository.RiskRepository;
import org.springframework.stereotype.Service;

@Service
public class RiskService {

    private RiskRepository repository;

    public RiskService(RiskRepository repository) {
        this.repository = repository;
    }

    public String getRisk(Integer id, String token){
        return repository.getRisk(id, token);
    }
}
