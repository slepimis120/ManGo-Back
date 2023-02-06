package mango.controller;

import jakarta.validation.Valid;
import mango.dto.*;
import mango.model.Location;
import mango.model.Ride;
import mango.model.RideLocation;
import mango.service.UnregisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"}, allowedHeaders = "*")
@RestController
@RequestMapping("/unregisteredUser")
public class UnregisteredUserController {

    @Autowired
    UnregisteredUserService service;

    @PreAuthorize("permitAll()")
    @PostMapping(consumes={"application/json"})
    public ResponseEntity rideEstimate(@RequestBody @Valid RideEstimatesDTO rideEstimates) {
        List<RideLocation> rideLocationList = new ArrayList<RideLocation>();
        for(RideLocationDTO locationDTO : rideEstimates.getLocations()){
            rideLocationList.add(new RideLocation(locationDTO));
        }
        CalculatedRideEstimatesDTO response = service.rideEstimate(rideLocationList, rideEstimates.getVehicleType(), rideEstimates.isBabyTransport(), rideEstimates.isPetTransport());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value="/calculatePrice",method = RequestMethod.PUT)
    public ResponseEntity calculatePrice(@RequestBody CalculatePriceDataDTO data) {
        CalculatedPriceDTO response = this.service.calculatePrice(data.getDistance(), data.getVehicleType());
        return ResponseEntity.status(HttpStatus.OK).body(response);
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
