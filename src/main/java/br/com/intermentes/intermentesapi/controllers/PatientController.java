package br.com.intermentes.intermentesapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")

public class PatientController {


    @GetMapping
    public String teste(){
        return "akkaka";
    }



}
