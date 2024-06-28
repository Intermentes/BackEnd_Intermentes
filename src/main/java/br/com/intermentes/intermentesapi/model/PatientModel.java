package br.com.intermentes.intermentesapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "patient")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PatientModel {

    @Column(name = "id", nullable = false, unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @OneToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @OneToMany(mappedBy = "patient")
    private Set<AppointmentModel> appointmentModels = new LinkedHashSet<>();

}
