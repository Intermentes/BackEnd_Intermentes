package br.com.intermentes.intermentesapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
public class JwtAuthResponse {
    
    private String token;
}
