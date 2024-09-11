package com.medilabo.front.controller;

import com.medilabo.front.model.Note;
import com.medilabo.front.model.Patient;
import com.medilabo.front.service.NoteService;
import com.medilabo.front.service.PatientService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDate;

@Controller
public class NoteController {

    private PatientService patientService;
    private NoteService noteService;

    public NoteController(PatientService patientService, NoteService noteService) {
        this.patientService = patientService;
        this.noteService = noteService;
    }

    @GetMapping("/addNote")
    public String getAddPatient(@RequestParam(name = "id") Integer id, Model model, HttpSession session) {
        String token = session.getAttribute("token").toString();
        Patient patient = patientService.getPatientById(id, token);
        if(patient != null){
            model.addAttribute("id", id);
            return "addNote.html";
        }
        return "redirect:patients?error=illegalArgument";
    }
    @PostMapping("/addNote")
    public String postAddPatient(@RequestParam(name = "id") Integer id, @Valid @ModelAttribute(name = "note") Note note, HttpSession session) throws IOException {
        String token = session.getAttribute("token").toString();
        note.setDate(LocalDate.now());
        note.setPatientId(id);
        if(note.getDoctor()==""){
            note.setDoctor("Unknown");
        }
        noteService.postNote(note, token);
        return "redirect:patientInfo?id="+id.toString();
    }

}
