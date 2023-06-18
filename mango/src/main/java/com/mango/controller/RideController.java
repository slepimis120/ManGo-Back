package com.mango.controller;


import com.mango.dto.*;
import com.mango.mapper.PanicDTOMapper;
import com.mango.mapper.RejectionDTOMapper;
import com.mango.mapper.RideDTOMapper;
import com.mango.model.*;
import com.mango.service.RideService;
import jakarta.validation.Valid;
import com.mango.dto.*;
import com.mango.model.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/ride")
public class RideController {

    @Autowired
    RideService rideService;

    @PreAuthorize("hasAuthority(\"ROLE_PASSENGER\")")
    @PostMapping
    public ResponseEntity createRide(@RequestBody @Valid CreateRideDTO ride) throws IOException{
        Ride newRide = new Ride(ride);
        for(Passenger passenger: newRide.getPassengers()){
            if(rideService.findActiveByPassenger(passenger.getId()) != null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot create a ride while you have one already pending!");
            }
        }
        Ride response = rideService.insert(newRide);
        ResponseRideDTO responseRide = new ResponseRideDTO(response);
        return ResponseEntity.status(HttpStatus.OK).body(responseRide);
    }

    @PreAuthorize("hasAuthority(\"ROLE_ADMIN\")")
    @GetMapping("/{id}")
    public ResponseEntity findRide(@PathVariable Integer id) {
        Ride ride = rideService.findById(id);
        if(ride == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ride does not exist!");
        }
        ResponseRideDTO responseRide = new ResponseRideDTO(ride);
        return ResponseEntity.status(HttpStatus.OK).body(responseRide);
    }

    @PreAuthorize("hasAuthority(\"ROLE_ADMIN\")")
    @GetMapping("/driver/{driverId}/active")
    public ResponseEntity findActiveByDriver(@PathVariable Integer driverId) {
        Ride ride = rideService.findActiveByDriver(driverId);
        if(ride == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Active ride does not exist!");
        }
        ResponseRideDTO responseRide = new ResponseRideDTO(ride);
        return ResponseEntity.status(HttpStatus.OK).body(responseRide);
    }

    @PreAuthorize("hasAuthority(\"ROLE_ADMIN\")")
    @GetMapping("/passenger/{passengerId}/active")
    public ResponseEntity findByPassenger(@PathVariable Integer passengerId) {
        Ride ride = rideService.findActiveByPassenger(passengerId);
        if(ride == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Active ride does not exist!");
        }
        RideDTOMapper mapper = new RideDTOMapper(new ModelMapper());
        ResponseRideDTO responseRide = mapper.fromRidetoResponseDTO(ride);
        return ResponseEntity.status(HttpStatus.OK).body(responseRide);
    }

    @PreAuthorize("hasAuthority(\"ROLE_PASSENGER\")")
    @PutMapping("/{id}/withdraw")
    public ResponseEntity cancelByPassenger(@PathVariable Integer id) {
        Ride ride = rideService.findById(id);
        if(ride == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ride does not exist!");
        }
        if(ride.getStatus() != Ride.Status.pending && ride.getStatus() != Ride.Status.accepted){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot cancel a ride that is not in status PENDING or STARTED!");
        }
        ride = rideService.cancelByPassenger(id);
        ResponseRideDTO responseRide = new ResponseRideDTO(ride);
        return ResponseEntity.status(HttpStatus.OK).body(responseRide);
    }

    @PreAuthorize("hasAuthority(\"ROLE_DRIVER\")")
    @PutMapping("/{id}/accept")
    public ResponseEntity accept(@PathVariable Integer id) {
        Ride ride = rideService.findById(id);
        if(ride == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ride does not exist!");
        }
        if(ride.getStatus() != Ride.Status.pending){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot accept a ride that is not in status PENDING!");
        }
        ride = rideService.accept(id);
        ResponseRideDTO responseRide = new ResponseRideDTO(ride);
        return ResponseEntity.status(HttpStatus.OK).body(responseRide);
    }

    @PreAuthorize("hasAuthority(\"ROLE_DRIVER\")")
    @PutMapping("{id}/start")
    public ResponseEntity start(@PathVariable Integer id){
        Ride ride = rideService.findById(id);
        if (ride == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ride does not exist!");
        }
        if(ride.getStatus() != Ride.Status.accepted){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot start a ride that is not in status ACCEPTED!");
        }
        ride = rideService.start(id);
        ResponseRideDTO responseRide = new ResponseRideDTO(ride);
        return ResponseEntity.status(HttpStatus.OK).body(responseRide);
    }

    @PreAuthorize("hasAuthority(\"ROLE_DRIVER\")")
    @PutMapping("/{id}/end")
    public ResponseEntity end(@PathVariable Integer id){
        Ride ride = rideService.findById(id);
        if(ride == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ride does not exist!");
        }
        if(ride.getStatus() != Ride.Status.accepted){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot end a ride that is not in status ACCEPTED!");
        }
        ride = rideService.end(id);
        ResponseRideDTO responseRide = new ResponseRideDTO(ride);
        return ResponseEntity.status(HttpStatus.OK).body(responseRide);
    }

    @PreAuthorize("hasAuthority(\"ROLE_DRIVER\")")
    @PutMapping("/{id}/cancel")
    public ResponseEntity cancelByDriver(@PathVariable Integer id, @RequestBody @Valid RejectionDTO rejection){
        RejectionDTOMapper rejectionMapper = new RejectionDTOMapper(new ModelMapper());
        Rejection newRejection = rejectionMapper.fromDTOtoRejection(rejection);
        Ride ride = rideService.findById(id);
        if(ride == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ride does not exist!");
        }
        if(ride.getStatus() != Ride.Status.pending){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot cancel a ride that is not in status PENDING!");
        }
        newRejection.setRide(ride);
        ride = rideService.cancelByDriver(id, newRejection);
        ResponseRideDTO responseRide = new ResponseRideDTO(ride);
        return ResponseEntity.status(HttpStatus.OK).body(responseRide);
    }

    @PreAuthorize("hasAuthority(\"ROLE_PASSENGER\")")
    @PutMapping("/{id}/panic")
    public ResponseEntity panic(@PathVariable Integer id, @RequestBody @Valid PanicDTO panic){
        PanicDTOMapper mapper = new PanicDTOMapper(new ModelMapper());
        Panic newPanic = mapper.fromDTOtoPanic(panic);
        Ride ride = rideService.findById(id);
        if(ride == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ride does not exist!");
        }
        Panic responsePanic = rideService.panic(id, newPanic);
        return ResponseEntity.status(HttpStatus.OK).body(responsePanic);
    }

    @PreAuthorize("hasAuthority(\"ROLE_PASSENGER\")")
    @PostMapping("/favorites")
    public ResponseEntity setFavoriteLocations(@RequestBody @Valid GetFavoriteLocationsDTO getFavoriteLocationsDTO){
        if(rideService.getTotalCount(5) >= 2){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Number of favorite rides cannot exceed 10!");
        }
        FavoriteLocations favoriteLocations = rideService.addFavoriteLocations(getFavoriteLocationsDTO);
        SendFavoriteLocationsDTO sendFavoriteLocationsDTO = new SendFavoriteLocationsDTO(favoriteLocations);
        return ResponseEntity.status(HttpStatus.OK).body(sendFavoriteLocationsDTO);
    }

    @PreAuthorize("hasAuthority(\"ROLE_PASSENGER\")")
    @GetMapping("/favorites")
    public ResponseEntity getFavoriteLocations(){
        List<SendFavoriteLocationsDTO> sendFavoriteLocationsDTOList = rideService.getFavoriteLocations(5);
        return ResponseEntity.status(HttpStatus.OK).body(sendFavoriteLocationsDTOList);
    }

    @PreAuthorize("hasAuthority(\"ROLE_PASSENGER\")")
    @DeleteMapping("/favorites/{id}")
    public ResponseEntity deleteFavoriteLocation(@PathVariable Integer id){
        if(rideService.deleteFavoriteLocations(id)){
            return ResponseEntity.status(HttpStatus.OK).body("Successful deletion of favorite location!");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Favorite location does not exist!");
        }
    }






    @PutMapping("/getAvailableDrivers")
    public ResponseEntity getAvailableDriver(@RequestBody @Valid CreateRideDTO createRideDTO) throws IOException {
        Ride ride = new Ride(createRideDTO);
        if(rideService.getVehicleCount(ride) == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Selected type of vehicle doesn't exist");
        }
        AvailableDriverDTO driver = rideService.getSuitableDrivers(ride);
        return ResponseEntity.status(HttpStatus.OK).body(driver);
    }

    @PreAuthorize("hasAuthority(\"ROLE_DRIVER\")")
    @GetMapping("/{driverId}/isAssigned")
    public ResponseEntity isDriverAssigned(@PathVariable Integer driverId) {
        Ride ride = rideService.isDriverAssigned(driverId);

        if(ride == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pending ride does not exist!");
        }
        ResponseRideDTO responseRide = new ResponseRideDTO(ride);
        return ResponseEntity.status(HttpStatus.OK).body(responseRide);
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
