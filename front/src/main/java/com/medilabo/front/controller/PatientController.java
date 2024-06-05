package com.medilabo.front.controller;

import com.medilabo.front.model.Patient;
import com.medilabo.front.model.PatientDTO;
import com.medilabo.front.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class PatientController {
    private PatientService patientService;
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients")
    public String getPatients(@RequestParam Optional<String> search, Model model) {
        if(search.isPresent()) {
            List<Patient> patients = patientService.getPatientsListBySearchedString(search.get());
            model.addAttribute("patients", patients);
        }
        return "patients.html";
    }
    @PostMapping("/patients")
    public String postPatients(@ModelAttribute(name = "search") String search, BindingResult result) {
        if (result.hasErrors()) {
            return "error";
        }
        return "redirect:patients?search="+search;
    }

    @GetMapping("/addPatient")
    public String getAddPatient() {
        return "addPatient.html";
    }
    @PostMapping("/addPatient")
    public String postAddPatient(@ModelAttribute(name = "patientInfo") PatientDTO patient) throws IOException {
        patientService.postPatient(patient);
        return "redirect:patients";
    }

    @GetMapping("/patientInfo")
    public String getPatient(@RequestParam Integer id, Model model) {
        Patient patient = patientService.getPatientById(id);
        model.addAttribute(patient);
        return "patientInfo.html";
    }

    @GetMapping("/editPatient")
    public String getEditPatient(@RequestParam Integer id, Model model){
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);
        return "editPatient.html";
    }
    @PostMapping("/editPatient")
    public String putEditPatient(@Valid @ModelAttribute(name = "patientInfo") PatientDTO patientDTO) throws IOException {
        patientService.putPatient(patientDTO);
        return "redirect:patients";
    }

    @GetMapping("/deletePatient")  //Is it a good way to do it ?
    public String deletePatient(@RequestParam Integer id){
        patientService.deletePatient(id);
        return "redirect:patients";
    }

}
