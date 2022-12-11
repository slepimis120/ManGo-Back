package mango.controller;

import mango.dto.RideEstimatesDTO;
import mango.model.RideEstimates;
import mango.service.UnregisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/unregisteredUser/")
public class UnregisteredUserController {

    @Autowired
    UnregisteredUserService service;

    @PostMapping
    public ResponseEntity rideEstimate(@RequestBody RideEstimatesDTO rideEstimates) {
        RideEstimates response = service.rideEstimate(rideEstimates.getLocations(), rideEstimates.getVehicleType(), rideEstimates.isBabyTransport(), rideEstimates.isPetTransport());
        return new ResponseEntity(response, HttpStatus.OK);
    }

}