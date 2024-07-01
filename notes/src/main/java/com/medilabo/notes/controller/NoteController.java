package com.medilabo.notes.controller;

import com.medilabo.notes.model.Note;
import com.medilabo.notes.model.Patient;
import com.medilabo.notes.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/note")
public class NoteController {

    private NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity getNotes(@RequestBody Patient patient){
        List<Note> notes = service.getNoteByPatient(patient);
        if (notes.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No note found for this patient");
        }
        else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(notes);
        }
    }

    @PostMapping
    public ResponseEntity postNote(@RequestBody Note note){
        note.setDate(LocalDate.now());
        if(service.postNote(note)){
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Note created successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("An error occured");
        }


    }
}
