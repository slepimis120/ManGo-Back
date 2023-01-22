package mango.controller;

import mango.dto.CalculatedRideEstimatesDTO;
import mango.dto.LocationDTO;
import mango.dto.RideEstimatesDTO;
import mango.dto.RideLocationDTO;
import mango.model.Location;
import mango.model.RideLocation;
import mango.service.UnregisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"}, allowedHeaders = "*")
@RestController
@RequestMapping("/unregisteredUser/")
public class UnregisteredUserController {

    @Autowired
    UnregisteredUserService service;

    @PreAuthorize("permitAll()")
    @PostMapping(consumes={"application/json"})
    public ResponseEntity rideEstimate(@RequestBody RideEstimatesDTO rideEstimates) {
        List<RideLocation> rideLocationList = new ArrayList<RideLocation>();
        for(RideLocationDTO locationDTO : rideEstimates.getLocations()){
            rideLocationList.add(new RideLocation(locationDTO));
        }
        CalculatedRideEstimatesDTO response = service.rideEstimate(rideLocationList, rideEstimates.getVehicleType(), rideEstimates.isBabyTransport(), rideEstimates.isPetTransport());
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
