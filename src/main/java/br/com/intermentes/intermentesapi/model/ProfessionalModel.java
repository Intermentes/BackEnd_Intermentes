package br.com.intermentes.intermentesapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "professional")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProfessionalModel {

    @Column(name = "id", nullable = false, unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel user;

    @Column(name = "crp", nullable = false, length = 10, unique = true)
    private String crp;

    @OneToMany(mappedBy = "professional")
    private Set<AppointmentModel> appointmentModels = new LinkedHashSet<>();

}
