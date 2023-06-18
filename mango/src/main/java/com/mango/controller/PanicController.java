package com.mango.controller;

import com.mango.dto.PanicResponseDTO;
import com.mango.service.PanicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/panic")
public class PanicController {

    @Autowired
    PanicService service;

    @PreAuthorize("hasAuthority(\"ROLE_ADMIN\")")
    @GetMapping
    public ResponseEntity getAll(){
        PanicResponseDTO response = service.getAllResponse();
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
