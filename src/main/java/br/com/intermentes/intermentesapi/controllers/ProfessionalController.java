package br.com.intermentes.intermentesapi.controllers;

import br.com.intermentes.intermentesapi.dto.ProfessionalResponse;
import br.com.intermentes.intermentesapi.services.ProfessionalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/professionals")
@AllArgsConstructor
public class ProfessionalController {

    private final ProfessionalService professionalService;

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(professionalService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProfessionalResponse>> findAll() {
        return ResponseEntity.ok(professionalService.findAll());
    }
}
