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
import java.util.List;
import java.util.stream.Collectors;

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
                        .name(signUp.getName())
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


    public UserResponse getUserById(Long id) {
        UserModel user = userModelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
        return new UserResponse(user);
    }

    public UserResponse updateUser(Long id, SignUp signUp) {
        UserModel user = userModelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));

        user.setName(signUp.getName());
        user.setEmail(signUp.getEmail());
        user.setPassword(passwordEncoder.encode(signUp.getPassword()));
        user.setBirthDate(LocalDate.parse(signUp.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        user.setEthnicity(signUp.getEthnicity());
        user.setGender(signUp.getGender());
        user.setPhone(signUp.getPhone());
        user.setCpf(signUp.getCpf());


        return new UserResponse(userModelRepository.save(user));
    }

    public void deleteUser(Long id) {
        if (!userModelRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found.");
        }
        userModelRepository.deleteById(id);
    }

    public List<UserResponse> getAllUsers() {
        List<UserModel> users = userModelRepository.findAll();
        return users.stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }





}
