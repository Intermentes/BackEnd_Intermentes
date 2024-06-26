package br.com.intermentes.intermentesapi.services;

import br.com.intermentes.intermentesapi.dto.PatientResponse;
import br.com.intermentes.intermentesapi.dto.SignUp;
import br.com.intermentes.intermentesapi.model.PatientModel;
import br.com.intermentes.intermentesapi.model.UserModel;
import br.com.intermentes.intermentesapi.repositories.PatientModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PatientService {

    private final PatientModelRepository patientRepository;

    public PatientResponse findById(Long id) {
        PatientModel patient = patientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Patient not found"));
        return new PatientResponse(patient, true);
    }

    public List<PatientResponse> findAll() {
        return patientRepository.findAll().stream().map(patientModel -> new PatientResponse(patientModel, true)).collect(Collectors.toList());
    }

    public void save(UserModel user) {
        patientRepository.save(PatientModel.builder()
                .user(user)
                .build());
    }
}
