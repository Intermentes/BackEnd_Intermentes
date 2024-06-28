package br.com.intermentes.intermentesapi.dto;

import br.com.intermentes.intermentesapi.model.enums.Ethnicity;
import br.com.intermentes.intermentesapi.model.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUp {


    private String name;

    private String email;

    private String password;

    private String birthDate;

    private Gender gender;

    private Ethnicity ethnicity;

    private String phone;

    private String cpf;

    private String crp;
}
