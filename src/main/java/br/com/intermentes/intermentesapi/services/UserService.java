package br.com.intermentes.intermentesapi.services;

import br.com.intermentes.intermentesapi.model.ProfessionalModel;
import br.com.intermentes.intermentesapi.repositories.UserModelRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class UserService {

    private final UserModelRepository userModelRepository;

    @Transactional
    public UserDetailsService userDetailsService() {
        return username ->
                userModelRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found with email - " + username));
    }



}
