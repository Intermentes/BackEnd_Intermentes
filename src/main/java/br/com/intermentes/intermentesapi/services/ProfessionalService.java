package br.com.intermentes.intermentesapi.services;

import br.com.intermentes.intermentesapi.model.ProfessionalModel;
import br.com.intermentes.intermentesapi.model.UserModel;
import br.com.intermentes.intermentesapi.repositories.ProfessionalModelRepository;
import br.com.intermentes.intermentesapi.repositories.UserModelRepository;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ProfessionalService {


    private final ProfessionalModelRepository professionalModelRepository;

    public ProfessionalService(ProfessionalModelRepository professionalModelRepository) {
        this.professionalModelRepository = professionalModelRepository;
    }


    @Transactional
    public ProfessionalModel save(ProfessionalModel professional) {
        return professionalModelRepository.save(professional);
    }






}



