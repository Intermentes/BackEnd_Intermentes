package br.com.intermentes.intermentesapi.dto;

import lombok.Data;

@Data
public class AppointmentRequest {
    private Long patientId;
    private Long proffesionalId;
    private String date;
    private String notes;
}
