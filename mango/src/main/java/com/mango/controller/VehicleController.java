package com.mango.controller;

import com.mango.dto.LocationDTO;
import com.mango.model.Location;
import com.mango.model.Vehicle;
import jakarta.validation.Valid;
import com.mango.dto.VehicleDTO;
import com.mango.mapper.LocationDTOMapper;
import com.mango.service.VehicleService;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    VehicleService service;

    private SimpMessagingTemplate simpMessagingTemplate;

    @PreAuthorize("hasAuthority(\"ROLE_DRIVER\")")
    @PutMapping("/{id}/location")
    public ResponseEntity updateLocation(@RequestBody @Valid LocationDTO locationDTO, @PathVariable Integer id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Vehicle vehicle = service.findOne(id);
        if (vehicle == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle does not exist!");
        }

        LocationDTOMapper mapper = new LocationDTOMapper(new ModelMapper());
        Location location = mapper.fromDTOtoLocation(locationDTO);
        service.insertNewLocation(location);
        vehicle.setCurrentLocation(location);

        service.save(vehicle);
        this.simpMessagingTemplate.convertAndSend("/map-updates/update-vehicle-position", new VehicleDTO(vehicle));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Coordinates successfully updated");
    }

    @GetMapping
    public ResponseEntity getVehicles(){
        List<VehicleDTO> list = new ArrayList<>();
        for(Vehicle vehicle : service.findAll()){
            list.add(new VehicleDTO(vehicle));
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }
}
