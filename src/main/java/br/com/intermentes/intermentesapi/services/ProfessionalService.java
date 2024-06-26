package br.com.intermentes.intermentesapi.services;

import br.com.intermentes.intermentesapi.dto.ProfessionalResponse;
import br.com.intermentes.intermentesapi.dto.SignUp;
import br.com.intermentes.intermentesapi.model.ProfessionalModel;
import br.com.intermentes.intermentesapi.model.UserModel;
import br.com.intermentes.intermentesapi.repositories.ProfessionalModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProfessionalService {

    private final ProfessionalModelRepository professionalRepository;

    public ProfessionalResponse findById(Long id) {
        ProfessionalModel professional = professionalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professional not found"));
        return new ProfessionalResponse(professional, true);
    }

    public List<ProfessionalResponse> findAll() {
        return professionalRepository.findAll().stream().map(professionalModel -> new ProfessionalResponse(professionalModel, true)).collect(Collectors.toList());
    }

    public void save(SignUp signUp, UserModel user) {
        professionalRepository.save(ProfessionalModel.builder()
                .user(user)
                .crp(signUp.getCrp())
                .build());
    }
}
