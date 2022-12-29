package mango.controller;


import mango.dto.CreateRideDTO;
import mango.dto.PanicDTO;
import mango.dto.RejectionDTO;
import mango.dto.ResponseRideDTO;
import mango.mapper.PanicDTOMapper;
import mango.mapper.RejectionDTOMapper;
import mango.mapper.RideDTOMapper;
import mango.model.Panic;
import mango.model.Rejection;
import mango.model.Ride;
import mango.service.interfaces.IRideService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/ride")
public class RideController {

    @Autowired
    IRideService service;

    @PostMapping
    public ResponseEntity insertVehicle(@RequestBody CreateRideDTO ride){

        RideDTOMapper mapper = new RideDTOMapper(new ModelMapper());
        Ride newRide = mapper.fromCreateDTOtoRide(ride);
        Ride response = service.insert(newRide);
        if(response == null){
            return new ResponseEntity("Error msg", HttpStatus.BAD_REQUEST);
        }
        ResponseRideDTO responseRide = mapper.fromRidetoResponseDTO(response);
        return new ResponseEntity(responseRide, HttpStatus.OK);}

    @GetMapping("/{id}")
    public ResponseEntity findRide(@PathVariable Integer id) {
        Ride ride = service.find(id);
        if(ride == null){
            return new ResponseEntity("Error msg", HttpStatus.BAD_REQUEST);
        }
        RideDTOMapper mapper = new RideDTOMapper(new ModelMapper());
        ResponseRideDTO responseRide = mapper.fromRidetoResponseDTO(ride);
        return new ResponseEntity(responseRide, HttpStatus.OK);
    }
    @GetMapping("/driver/{driverId}/active")
    public ResponseEntity findByDriver(@PathVariable Integer driverId) throws Exception {
        Ride ride = service.findByDriver(driverId);
        if(ride == null){
            return new ResponseEntity("Error msg", HttpStatus.BAD_REQUEST);
        }
        RideDTOMapper mapper = new RideDTOMapper(new ModelMapper());
        ResponseRideDTO responseRide = mapper.fromRidetoResponseDTO(ride);
        return new ResponseEntity(responseRide, HttpStatus.OK);
    }

    @GetMapping("/passenger/{passengerId}/active")
    public ResponseEntity findByPassenger(@PathVariable Integer passengerId) {
        Ride ride = service.findByPassenger(passengerId);
        if(ride == null){
            return new ResponseEntity("Error msg", HttpStatus.BAD_REQUEST);
        }
        RideDTOMapper mapper = new RideDTOMapper(new ModelMapper());
        ResponseRideDTO responseRide = mapper.fromRidetoResponseDTO(ride);
        return new ResponseEntity(responseRide, HttpStatus.OK);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity cancelByPassenger(@PathVariable Integer id) {
        Ride ride = service.cancelByPassenger(id);
        if(ride == null){
            return new ResponseEntity("Error msg", HttpStatus.BAD_REQUEST);
        }
        RideDTOMapper mapper = new RideDTOMapper(new ModelMapper());
        ResponseRideDTO responseRide = mapper.fromRidetoResponseDTO(ride);
        return new ResponseEntity(responseRide, HttpStatus.OK);
    }

    @PutMapping("/{id}/accept")
    public ResponseEntity accept(@PathVariable Integer id) {
        Ride ride = service.accept(id);
        if(ride == null){
            return new ResponseEntity("Error msg", HttpStatus.BAD_REQUEST);
        }
        RideDTOMapper mapper = new RideDTOMapper(new ModelMapper());
        ResponseRideDTO responseRide = mapper.fromRidetoResponseDTO(ride);
        return new ResponseEntity(responseRide, HttpStatus.OK);
    }

    @PutMapping("/{id}/end")
    public ResponseEntity end(@PathVariable Integer id){
        Ride ride = service.end(id);
        if(ride == null){
            return new ResponseEntity("Error msg", HttpStatus.BAD_REQUEST);
        }
        RideDTOMapper mapper = new RideDTOMapper(new ModelMapper());
        ResponseRideDTO responseRide = mapper.fromRidetoResponseDTO(ride);
        return new ResponseEntity(responseRide, HttpStatus.OK);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity cancelByDriver(@PathVariable Integer id, @RequestBody RejectionDTO rejection){
        RejectionDTOMapper rejectionMapper = new RejectionDTOMapper(new ModelMapper());
        Rejection newRejection = rejectionMapper.fromDTOtoRejection(rejection);
        Ride ride = service.cancelByDriver(id, newRejection);
        if(ride == null){
            return new ResponseEntity("Error msg", HttpStatus.BAD_REQUEST);
        }
        RideDTOMapper rideMapper = new RideDTOMapper(new ModelMapper());
        ResponseRideDTO responseRide = rideMapper.fromRidetoResponseDTO(ride);
        return new ResponseEntity(responseRide, HttpStatus.OK);
    }

    @PutMapping("/{id}/panic")
    public ResponseEntity panic(@PathVariable Integer id, @RequestBody PanicDTO panic){
        PanicDTOMapper mapper = new PanicDTOMapper(new ModelMapper());
        Panic newPanic = mapper.fromDTOtoPanic(panic);
        Panic responsePanic = service.panic(id, newPanic);
        if(responsePanic == null){
            return new ResponseEntity("Error msg", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(responsePanic, HttpStatus.OK);
    }
}
