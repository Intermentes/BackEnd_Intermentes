package br.com.intermentes.intermentesapi.dto;

import br.com.intermentes.intermentesapi.model.ProfessionalModel;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Value
public class ProfessionalResponse implements Serializable {
    Long id;
    UserResponse user;
    String crp;
    Set<AppointmentResponse> appointments;

    public ProfessionalResponse(ProfessionalModel professionalModel, boolean loadAppointments) {
        this.id = professionalModel.getId();
        this.user = new UserResponse(professionalModel.getUser());
        this.crp = professionalModel.getCrp();
        if(loadAppointments) {
            this.appointments = professionalModel.getAppointmentModels().stream().map(AppointmentResponse::new).collect(
                    Collectors.toSet());
        }
        else{
            this.appointments = Set.of();
        }
    }

    public ProfessionalResponse(Long id, UserResponse userResponse, String crp) {
        this.id = id;
        this.user = userResponse;
        this.crp = crp;
        this.appointments = Set.of();
    }
}
