package mango.controller;

import mango.dto.PanicResponseDTO;
import mango.service.interfaces.IPanicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/panic")
public class PanicController {

    @Autowired
    IPanicService service;

    @GetMapping
    public ResponseEntity getAll(){
        PanicResponseDTO response = service.getAll();
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
