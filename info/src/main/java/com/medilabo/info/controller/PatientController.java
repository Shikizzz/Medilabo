package com.medilabo.info.controller;

import com.medilabo.info.model.Patient;
import com.medilabo.info.model.PatientDTO;
import com.medilabo.info.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/patient")
public class PatientController {
    private PatientService service;
    public PatientController(PatientService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity createPatient(@RequestBody PatientDTO patientDTO){
        Patient patient = patientDTO.convertToPatient(patientDTO);
        if(service.createPatient(patient)){
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Patient created successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Patient already in database. Maybe try to edit");
        }
    }
    @GetMapping
    public ResponseEntity readPatient(@RequestParam int id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.readPatient(id));
    }

    @GetMapping("/search")
    public ResponseEntity searchPatients(@RequestParam String search){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.searchPatient(search));
    }

    @PutMapping
    public ResponseEntity updatePatient(@RequestBody PatientDTO patientDTO){
        Patient patient = patientDTO.convertToPatient(patientDTO);
        if(service.updatePatient(patient)){
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Patient updated successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Patient not found");
        }
    }
    @DeleteMapping
    public ResponseEntity deletePatient(@RequestParam int id){
        if(service.deletePatient(id)){
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Patient deleted successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Patient not found");
        }
    }
}
