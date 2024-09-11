package com.medilabo.notes.controller;

import com.medilabo.notes.model.Note;
import com.medilabo.notes.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity getNotes(@RequestParam Integer id){
        List<Note> notes = service.getNoteByPatientId(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(notes);
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
