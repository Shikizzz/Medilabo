package com.medilabo.risk.service;

import com.medilabo.risk.model.Genre;
import com.medilabo.risk.model.Note;
import com.medilabo.risk.model.Patient;
import com.medilabo.risk.model.Risk;
import com.medilabo.risk.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

import static com.medilabo.risk.model.Genre.MALE;

@Service
public class RiskService {

    private PatientRepository repository;

    public RiskService(PatientRepository repository) {
        this.repository = repository;
    }

    public Risk getRisk(Integer id, String token){
        Patient patient = repository.getPatient(id, token);
        Genre genre = patient.getGenre();
        int age = calculateAge(patient.getBirthdate());
        int numberOfTerms = getNumberOfTerms(id, token);
        String patientType="";
        if(age>=30) {patientType="aged";}
        else if(genre.equals(MALE)){
            patientType="youngMale";
        }
        else{
            patientType="youngFemale";
        }
        Risk risk = Risk.UNKNOWN;
        switch(patientType){
            case "aged":
                if(numberOfTerms>=8){risk=Risk.EARLYONSET;}
                else if (numberOfTerms>=6){risk=Risk.DANGER;}
                else if (numberOfTerms>=2){risk=Risk.BORDERLINE;}
                else if (numberOfTerms==0){risk=Risk.NONE;}
                break;
            case "youngMale":
                if(numberOfTerms>=5){risk=Risk.EARLYONSET;}
                else if (numberOfTerms>=3){risk=Risk.DANGER;}
                else if (numberOfTerms==0){risk=Risk.NONE;}
                break;
            case "youngFemale":
                if(numberOfTerms>=7){risk=Risk.EARLYONSET;}
                else if (numberOfTerms>=4){risk=Risk.DANGER;}
                else if (numberOfTerms==0){risk=Risk.NONE;}
                break;
        }
        return risk;
    }

    private int calculateAge(LocalDate birthdate){
        LocalDate today = LocalDate.now();
        Period period = Period.between(birthdate, today);
        return period.getYears();
    }

    private int getNumberOfTerms(Integer id, String token){
        List<Note> notesWithMetadata = repository.getNotes(id, token);
        List<String> notes = new ArrayList<>();
        notesWithMetadata.stream().forEach(note -> notes.add(note.getContent()));
        List<String> terms = Arrays.asList(
                "hémoglobine a1c", "microalbumine", "taille", "poids","fumeur","fumeuse","anormal","cholestérol","vertige","rechute","réaction", "anticorps"
                );
        int counter = 0;
        for(String term : terms){
            for(String note : notes){
                if(note.toLowerCase().contains(term)){
                    counter++;
                    break;
                }
            }
        }
        return counter;
    }

}
