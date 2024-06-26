package br.com.intermentes.intermentesapi.repositories;

import br.com.intermentes.intermentesapi.model.ProfessionalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalModelRepository extends JpaRepository<ProfessionalModel, Long> {

}