package com.medilabo.notes.service;

import com.medilabo.notes.model.Note;
import com.medilabo.notes.model.Patient;
import com.medilabo.notes.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteRepository repository;

    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    public List<Note> getNoteByPatient(Patient patient){
        return repository.findByPatientOrderByDateDesc(patient);
    }

    public boolean postNote(Note note){
        try {
            repository.insert(note);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
