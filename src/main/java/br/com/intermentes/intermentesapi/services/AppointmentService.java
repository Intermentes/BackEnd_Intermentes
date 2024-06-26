package br.com.intermentes.intermentesapi.services;

import br.com.intermentes.intermentesapi.dto.*;
import br.com.intermentes.intermentesapi.model.AppointmentModel;
import br.com.intermentes.intermentesapi.model.PatientModel;
import br.com.intermentes.intermentesapi.model.ProfessionalModel;
import br.com.intermentes.intermentesapi.repositories.AppointmentRepository;
import br.com.intermentes.intermentesapi.repositories.PatientModelRepository;
import br.com.intermentes.intermentesapi.repositories.ProfessionalModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ProfessionalModelRepository professionalModelRepository;
    private final PatientModelRepository patientModelRepository;

    public AppointmentResponse create(AppointmentRequest request) {
        PatientModel patient = patientModelRepository.findById(request.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));
        ProfessionalModel professional = professionalModelRepository.findById(request.getProffesionalId())
                .orElseThrow(() -> new IllegalArgumentException("Professional not found"));

        AppointmentModel appointment = new AppointmentModel();
        appointment.setPatient(patient);
        appointment.setProfessional(professional);
        appointment.setAppointmentDate(LocalDateTime.parse(request.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        appointment.setNotes(request.getNotes());

        AppointmentModel savedAppointment = appointmentRepository.save(appointment);
        return toResponse(savedAppointment);
    }

    public List<AppointmentResponse> getAll() {
        return appointmentRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public AppointmentResponse getById(Long id) {
        return appointmentRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));
    }

    public AppointmentResponse update(Long id, AppointmentRequest request) {
        AppointmentModel appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));

        PatientModel patient = patientModelRepository.findById(request.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));
        ProfessionalModel professional = professionalModelRepository.findById(request.getProffesionalId())
                .orElseThrow(() -> new IllegalArgumentException("Professional not found"));

        appointment.setPatient(patient);
        appointment.setProfessional(professional);
        appointment.setAppointmentDate(LocalDateTime.parse(request.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        appointment.setNotes(request.getNotes());

        AppointmentModel updatedAppointment = appointmentRepository.save(appointment);
        return toResponse(updatedAppointment);
    }

    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }

    private AppointmentResponse toResponse(AppointmentModel model) {
        return new AppointmentResponse(
                new PatientResponse(model.getPatient().getId(), new UserResponse(model.getPatient().getUser())),
                new ProfessionalResponse(model.getProfessional().getId(), new UserResponse(model.getProfessional().getUser()), model.getProfessional().getCrp()),
                model.getAppointmentDate(),
                model.getNotes()
        );
    }
}
