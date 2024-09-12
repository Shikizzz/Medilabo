package com.medilabo.risk.controller;

import com.medilabo.risk.model.Risk;
import com.medilabo.risk.service.RiskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/risk")
public class RiskController {

    private RiskService service;

    public RiskController(RiskService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity getRisk(@RequestParam Integer id, @RequestParam String token){
        Risk risk = service.getRisk(id, token);
        return ResponseEntity.status(HttpStatus.OK)
                .body(risk);
    }

}
