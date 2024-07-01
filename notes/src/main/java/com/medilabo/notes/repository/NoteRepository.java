package com.medilabo.notes.repository;

import com.medilabo.notes.model.Note;
import com.medilabo.notes.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
    public List<Note> findByPatientOrderByDateDesc(Patient patient);
}
