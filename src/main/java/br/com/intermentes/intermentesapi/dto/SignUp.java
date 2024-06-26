package br.com.intermentes.intermentesapi.dto;

import br.com.intermentes.intermentesapi.model.enums.Ethnicity;
import br.com.intermentes.intermentesapi.model.enums.Gender;
import lombok.Data;

@Data
public class SignUp {

    private String email;

    private String password;

    private String birthDate;

    private Gender gender;

    private Ethnicity ethnicity;

    private String phone;

    private String cpf;

}
