package com.medilabo.front.service;

import com.medilabo.front.model.*;
import com.medilabo.front.repository.NoteRepository;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.List;

@Service
public class NoteService {

    private NoteRepository repository;

    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    public List<Note> getNotesByPatientId(Integer patientId){
        return repository.getNotesByPatientId(patientId);
    }


    public void postNote(Note note) throws IOException {
        repository.postNote(note);
    }
}
