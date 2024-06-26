package br.com.intermentes.intermentesapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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


    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel user;

    @Column(name = "crp", nullable = false, length = 10)
    private String crp;






}
