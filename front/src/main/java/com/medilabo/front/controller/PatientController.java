package com.medilabo.front.controller;

import com.medilabo.front.model.Note;
import com.medilabo.front.model.Patient;
import com.medilabo.front.model.PatientDTO;
import com.medilabo.front.service.NoteService;
import com.medilabo.front.service.PatientService;
import com.medilabo.front.service.RiskService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class PatientController {
    private PatientService patientService;
    private NoteService noteService;
    private RiskService riskService;
    public PatientController(PatientService patientService, NoteService noteService, RiskService riskService) {
        this.patientService = patientService;
        this.noteService = noteService;
        this.riskService = riskService;
    }

    @GetMapping(value= {"/"})
    public String getHome() {
        return "redirect:patients";
    }

    @GetMapping("/patients")
    public String getPatients(@RequestParam Optional<String> search, @RequestParam Optional<String> error, Model model) {
        if (error.isPresent()) {
            model.addAttribute("illegalArgumentError", true);
        }
        if(search.isPresent()) {
            List<Patient> patients = patientService.getPatientsListBySearchedString(search.get());
            if(patients.size()>0){
                model.addAttribute("patients", patients);
            }
            else model.addAttribute("noPatientFoundError", true);
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
    public String getAddPatient(@RequestParam Optional<String> error, Model model) {
        if(error.isPresent()){
            model.addAttribute("invalidPatientError", true);
        }
        return "addPatient.html";
    }
    @PostMapping("/addPatient")
    public String postAddPatient(@Valid @ModelAttribute(name = "patientInfo") PatientDTO patient, Model model, HttpSession session) throws IOException {
        patientService.postPatient(patient);
        return "redirect:patients";
    }

    @GetMapping("/patientInfo")
    public String getPatient(@RequestParam Integer id, Model model, HttpSession session) {
        try{
            Patient patient = patientService.getPatientById(id);
            model.addAttribute(patient);
            List<Note> notes = noteService.getNotesByPatientId(id);
            model.addAttribute("notes", notes);
            String risk = riskService.getRisk(id);
            model.addAttribute("risk", risk);
        }
        catch (IllegalArgumentException e){
            return "redirect:patients?error=illegalArgument";
        }
        return "patientInfo.html";
    }

    @GetMapping("/editPatient")
    public String getEditPatient(@RequestParam Integer id, Model model, HttpSession session){

        Patient patient = patientService.getPatientById(id);
        if(patient != null){
            model.addAttribute("patient", patient);
            return "editPatient.html";
        }
        return "redirect:patients?error=illegalArgument";
    }
    @PostMapping("/editPatient")
    public String putEditPatient(@Valid @ModelAttribute(name = "patientInfo") PatientDTO patientDTO, HttpSession session) throws IOException {
        patientService.putPatient(patientDTO);
        return "redirect:patients";
    }

    @GetMapping("/deletePatient")
    public String deletePatient(@RequestParam Integer id, HttpSession session){
        patientService.deletePatient(id);
        return "redirect:patients";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationErrors() {
        return "redirect:addPatient?error=invalid";
    }

}
