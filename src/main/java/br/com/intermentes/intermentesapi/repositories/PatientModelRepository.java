package br.com.intermentes.intermentesapi.repositories;

import br.com.intermentes.intermentesapi.model.PatientModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientModelRepository extends JpaRepository<PatientModel, Long> {
}
