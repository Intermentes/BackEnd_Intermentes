package br.com.intermentes.intermentesapi.model;

import jakarta.persistence.*;
import lombok.*;

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


}
