package br.com.intermentes.intermentesapi.dto;

import br.com.intermentes.intermentesapi.model.ProfessionalModel;
import br.com.intermentes.intermentesapi.model.UserModel;
import br.com.intermentes.intermentesapi.model.enums.Ethnicity;
import br.com.intermentes.intermentesapi.model.enums.Gender;
import br.com.intermentes.intermentesapi.model.enums.Roles;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;


@Getter
@Setter
@Data
@NoArgsConstructor

public class ProfessionalResponse {


    @Column(name = "email",nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "birthDate" )
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "ethnicity")
    private Ethnicity ethnicity;


// TODO: Implementar Imagem - Foto de Perfil

    @Column(name = "phone", nullable = false, length = 11)
    private String phone;

    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

    @Column(name = "crp", nullable = false, length = 10)
    private String crp;



}








