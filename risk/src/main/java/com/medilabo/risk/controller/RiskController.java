package com.medilabo.risk.controller;

import com.medilabo.risk.model.Risk;
import com.medilabo.risk.service.RiskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/risk")
public class RiskController {

    private RiskService service;

    public RiskController(RiskService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity getRisk(@RequestParam Integer id){
        Risk risk = service.getRisk(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(risk);
    }

}
