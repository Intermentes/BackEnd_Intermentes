package br.com.intermentes.intermentesapi.controllers;

import br.com.intermentes.intermentesapi.dto.ProfessionalResponse;
import br.com.intermentes.intermentesapi.model.ProfessionalModel;
import br.com.intermentes.intermentesapi.model.UserModel;
import br.com.intermentes.intermentesapi.services.ProfessionalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/professionals")
@AllArgsConstructor
public class ProfessionalController {

    private final ProfessionalService professionalService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<ProfessionalResponse> createProfessional(@Valid @RequestBody ProfessionalResponse professionalResponse) {

        String encodedPassword = passwordEncoder.encode(professionalResponse.getPassword());

        UserModel user = UserModel.builder()
                .email(professionalResponse.getEmail())
                .password(encodedPassword)
                .birthDate(professionalResponse.getBirthDate())
                .gender(professionalResponse.getGender())
                .ethnicity(professionalResponse.getEthnicity())
                .phone(professionalResponse.getPhone())
                .cpf(professionalResponse.getCpf())
                .build();

        ProfessionalModel professional = ProfessionalModel.builder()
                .user(user)
                .crp(professionalResponse.getCrp())
                .build();

        ProfessionalModel savedProfessional = professionalService.save(professional);

        ProfessionalResponse response = getProfessionalResponse(savedProfessional);

        return ResponseEntity.ok(response);
    }

    private static ProfessionalResponse getProfessionalResponse(ProfessionalModel savedProfessional) {
        ProfessionalResponse response = new ProfessionalResponse();
        response.setEmail(savedProfessional.getUser().getEmail());
        response.setPassword(savedProfessional.getUser().getPassword());
        response.setBirthDate(savedProfessional.getUser().getBirthDate());
        response.setGender(savedProfessional.getUser().getGender());
        response.setEthnicity(savedProfessional.getUser().getEthnicity());
        response.setCrp(savedProfessional.getCrp());
        response.setPhone(savedProfessional.getUser().getPhone());
        response.setCpf(savedProfessional.getUser().getCpf());
        return response;
    }
    }

