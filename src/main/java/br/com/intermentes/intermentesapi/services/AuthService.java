package br.com.intermentes.intermentesapi.services;

import br.com.intermentes.intermentesapi.dto.JwtAuthResponse;
import br.com.intermentes.intermentesapi.dto.LoginRequest;
import br.com.intermentes.intermentesapi.dto.SignUp;
import br.com.intermentes.intermentesapi.dto.UserResponse;
import br.com.intermentes.intermentesapi.model.UserModel;
import br.com.intermentes.intermentesapi.repositories.UserModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
public class AuthService {

    private final JwtServices jwtServices;
    private final AuthenticationManager authenticationManager;
    private final UserModelRepository userModelRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProfessionalService professionalService;
    private final PatientService patientService;

    public JwtAuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userModelRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtServices.generateToken(user);
        return new JwtAuthResponse(jwt);
    }

    public UserResponse signUp(SignUp signUp) {
        UserModel user = this.userModelRepository.save(
                UserModel.builder()
                        .email(signUp.getEmail())
                        .password(passwordEncoder.encode(signUp.getPassword()))
                        .birthDate(LocalDate.parse(signUp.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                        .ethnicity(signUp.getEthnicity())
                        .gender(signUp.getGender())
                        .phone(signUp.getPhone())
                        .cpf(signUp.getCpf())
                        .build()
        );
        UserResponse userResponse = new UserResponse(user);
        if (signUp.getCrp() != null) {
            professionalService.save(signUp, user);
        } else {
            patientService.save(user);
        }
        return userResponse;
    }





}
