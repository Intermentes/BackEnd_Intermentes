package br.com.intermentes.intermentesapi.dto;

import br.com.intermentes.intermentesapi.model.UserModel;
import br.com.intermentes.intermentesapi.model.enums.Ethnicity;
import br.com.intermentes.intermentesapi.model.enums.Gender;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserResponse {

    private Long id;

    private String email;

    private LocalDate birthDate;

    private Gender gender;

    private Ethnicity ethnicity;

    private String phone;

    public UserResponse(UserModel userModel){
        this.id = userModel.getId();
        this.email = userModel.getEmail();
        this.birthDate = userModel.getBirthDate();
        this.gender = userModel.getGender();
        this.ethnicity = userModel.getEthnicity();
        this.phone = userModel.getPhone();
    }
}
