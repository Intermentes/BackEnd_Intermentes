package br.com.intermentes.intermentesapi.dto;

import br.com.intermentes.intermentesapi.model.PatientModel;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Value
public class PatientResponse implements Serializable {
    Long id;
    UserResponse user;
    Set<AppointmentResponse> appointments;

    public PatientResponse(PatientModel patientModel, boolean loadAppointments) {
        this.id = patientModel.getId();
        this.user = new UserResponse(patientModel.getUser());
        if(loadAppointments){
        this.appointments = patientModel.getAppointmentModels().stream().map(AppointmentResponse::new).collect(
                Collectors.toSet());
        }
        else{
            this.appointments = Set.of();

        }
    }

    public PatientResponse(Long id, UserResponse userResponse) {
        this.id = id;
        this.user = userResponse;
        this.appointments = Set.of();
    }
}
