package br.com.intermentes.intermentesapi.dto;

import br.com.intermentes.intermentesapi.model.AppointmentModel;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link br.com.intermentes.intermentesapi.model.AppointmentModel}
 */
@Value
public class AppointmentResponse implements Serializable {
    PatientResponse patient;
    ProfessionalResponse professional;
    LocalDateTime appointmentDate;
    String notes;

    public AppointmentResponse(AppointmentModel appointmentModel) {
        this.patient = new PatientResponse(appointmentModel.getPatient(), false);
        this.professional = new ProfessionalResponse(appointmentModel.getProfessional(), false);
        this.appointmentDate = appointmentModel.getAppointmentDate();
        this.notes = appointmentModel.getNotes();
    }

    public AppointmentResponse(PatientResponse patientResponse, ProfessionalResponse professionalResponse, LocalDateTime appointmentDate, String notes) {
        this.patient = patientResponse;
        this.professional = professionalResponse;
        this.appointmentDate = appointmentDate;
        this.notes = notes;
    }
}
