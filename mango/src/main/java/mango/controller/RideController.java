package mango.controller;


import mango.dto.*;
import mango.mapper.PanicDTOMapper;
import mango.mapper.RejectionDTOMapper;
import mango.mapper.RideDTOMapper;
import mango.model.*;
import mango.service.RideService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/ride")
public class RideController {

    @Autowired
    RideService rideService;

    @PostMapping
    public ResponseEntity createRide(@RequestBody CreateRideDTO ride){
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

    @GetMapping("/{id}")
    public ResponseEntity findRide(@PathVariable Integer id) {
        Ride ride = rideService.findById(id);
        if(ride == null){
            return new ResponseEntity("Ride does not exist!", HttpStatus.BAD_REQUEST);
        }
        ResponseRideDTO responseRide = new ResponseRideDTO(ride);
        return ResponseEntity.status(HttpStatus.OK).body(responseRide);
    }

    @GetMapping("/driver/{driverId}/active")
    public ResponseEntity findActiveByDriver(@PathVariable Integer driverId) {
        Ride ride = rideService.findActiveByDriver(driverId);
        if(ride == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Active ride does not exist!");
        }
        ResponseRideDTO responseRide = new ResponseRideDTO(ride);
        return ResponseEntity.status(HttpStatus.OK).body(responseRide);
    }

    @GetMapping("/passenger/{passengerId}/active")
    public ResponseEntity findByPassenger(@PathVariable Integer passengerId) {
        Ride ride = rideService.findActiveByPassenger(passengerId);
        if(ride == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Active ride does not exist!");
        }
        RideDTOMapper mapper = new RideDTOMapper(new ModelMapper());
        ResponseRideDTO responseRide = mapper.fromRidetoResponseDTO(ride);
        return ResponseEntity.status(HttpStatus.OK).body(responseRide);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity cancelByPassenger(@PathVariable Integer id) {
        Ride ride = rideService.findById(id);
        if(ride == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ride does not exist!");
        }
        if(ride.getStatus() != Ride.Status.pending && ride.getStatus() != Ride.Status.accepted){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot cancel a ride that is not in status PENDING or STARTED!");
        }
        ride = rideService.cancelByPassenger(id);
        ResponseRideDTO responseRide = new ResponseRideDTO(ride);
        return ResponseEntity.status(HttpStatus.OK).body(responseRide);
    }

    @PutMapping("/{id}/accept")
    public ResponseEntity accept(@PathVariable Integer id) {
        Ride ride = rideService.findById(id);
        if(ride == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ride does not exist!");
        }
        if(ride.getStatus() != Ride.Status.pending){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot cancel a ride that is not in status PENDING!");
        }
        ride = rideService.accept(id);
        ResponseRideDTO responseRide = new ResponseRideDTO(ride);
        return ResponseEntity.status(HttpStatus.OK).body(responseRide);
    }

    @PutMapping("/{id}/end")
    public ResponseEntity end(@PathVariable Integer id){
        Ride ride = rideService.findById(id);
        if(ride == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ride does not exist!");
        }
        if(ride.getStatus() != Ride.Status.accepted){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot cancel a ride that is not in status ACCEPTED!");
        }
        ride = rideService.end(id);
        ResponseRideDTO responseRide = new ResponseRideDTO(ride);
        return ResponseEntity.status(HttpStatus.OK).body(responseRide);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity cancelByDriver(@PathVariable Integer id, @RequestBody RejectionDTO rejection){
        RejectionDTOMapper rejectionMapper = new RejectionDTOMapper(new ModelMapper());
        Rejection newRejection = rejectionMapper.fromDTOtoRejection(rejection);
        Ride ride = rideService.findById(id);
        if(ride == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ride does not exist!");
        }
        if(ride.getStatus() != Ride.Status.pending){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot cancel a ride that is not in status PENDING!");
        }
        newRejection.setRide(ride);
        ride = rideService.cancelByDriver(id, newRejection);
        ResponseRideDTO responseRide = new ResponseRideDTO(ride);
        return ResponseEntity.status(HttpStatus.OK).body(responseRide);
    }

    @PutMapping("/{id}/panic")
    public ResponseEntity panic(@PathVariable Integer id, @RequestBody PanicDTO panic){
        PanicDTOMapper mapper = new PanicDTOMapper(new ModelMapper());
        Panic newPanic = mapper.fromDTOtoPanic(panic);
        Ride ride = rideService.findById(id);
        if(ride == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ride does not exist!");
        }
        Panic responsePanic = rideService.panic(id, newPanic);
        return ResponseEntity.status(HttpStatus.OK).body(responsePanic);
    }

    @PostMapping("/favorites")
    public ResponseEntity setFavoriteLocations(@RequestBody GetFavoriteLocationsDTO getFavoriteLocationsDTO){
        if(rideService.getTotalCount(5) >= 10){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Number of favorite rides cannot exceed 10!");
        }
        FavoriteLocations favoriteLocations = rideService.addFavoriteLocations(getFavoriteLocationsDTO);
        SendFavoriteLocationsDTO sendFavoriteLocationsDTO = new SendFavoriteLocationsDTO(favoriteLocations);
        return ResponseEntity.status(HttpStatus.OK).body(sendFavoriteLocationsDTO);
    }

    @GetMapping("/favorites")
    public ResponseEntity getFavoriteLocations(){
        List<SendFavoriteLocationsDTO> sendFavoriteLocationsDTOList = rideService.getFavoriteLocations(5);
        return ResponseEntity.status(HttpStatus.OK).body(sendFavoriteLocationsDTOList);
    }

    @DeleteMapping("favorites/{id}")
    public ResponseEntity deleteFavoriteRide(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/getAvailableDrivers")
    public ResponseEntity getAvailableDriver(@RequestBody CreateRideDTO createRideDTO) throws IOException {
        Ride ride = new Ride(createRideDTO);
        if(rideService.getVehicleCount(ride) == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Selected type of vehicle doesn't exist");
        }
        rideService.getSuitableDrivers(ride);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/driver/{driverId}/isAssigned")
    public ResponseEntity isDriverAssigned(@PathVariable Integer driverId) {
        Ride ride = rideService.isDriverAssigned(driverId);
        if(ride == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pending ride does not exist!");
        }
        ResponseRideDTO responseRide = new ResponseRideDTO(ride);
        return ResponseEntity.status(HttpStatus.OK).body(responseRide);
    }
}
