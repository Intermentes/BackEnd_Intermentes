package br.com.intermentes.intermentesapi.repositories;

import br.com.intermentes.intermentesapi.model.AppointmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<AppointmentModel, Long> {
}
