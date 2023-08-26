package com.mango.controller;

import com.mango.service.*;
import jakarta.validation.Valid;
import com.mango.dto.GetReviewDTO;
import com.mango.dto.ReviewOverviewDTO;
import com.mango.dto.ReviewResponseDTO;
import com.mango.model.Review;
import com.mango.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    DriverService driverService;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    RideService rideService;

    @Autowired
    PassengerService passengerService;

    @PreAuthorize("hasAuthority(\"ROLE_PASSENGER\")")
    @PostMapping("/{rideId}/vehicle/{id}")
    public ResponseEntity sendVehicleReview(@RequestBody @Valid GetReviewDTO review, @PathVariable("id") Integer id, @PathVariable("rideId") Integer rideId) {
        if(!rideService.ifRideExists(rideId)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ride does not exist!");
        }else if(!passengerService.isPassengerInRide(rideId, id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Passenger does not exist or isn't in mentioned ride!");
        }{
            Review newReview = new Review(review);
            newReview.setReviewType(Review.Type.VEHICLE);
            Review response = reviewService.sendReview(id, rideId, newReview);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @PreAuthorize("hasAuthority(\"ROLE_PASSENGER\")")
    @GetMapping("/vehicle/{id}")
    public ResponseEntity getVehicleReviews(@PathVariable Integer id) {
        if(!vehicleService.ifVehicleExists(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle does not exist!");
        }else{
            ReviewResponseDTO response = reviewService.getVehicleReviews(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }


    @PreAuthorize("hasAuthority(\"ROLE_PASSENGER\")")
    @PostMapping("/{rideId}/driver/{id}")
    public ResponseEntity sendDriverReview(@RequestBody @Valid GetReviewDTO review, @PathVariable("id") Integer id, @PathVariable("rideId") Integer rideId)
        {
            if(!rideService.ifRideExists(rideId)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ride does not exist!");
            }else{
                Review newReview = new Review(review);
                newReview.setReviewType(Review.Type.DRIVER);
                Review response = reviewService.sendReview(id, rideId, newReview);
                return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @PreAuthorize("hasAuthority(\"ROLE_PASSENGER\")")
    @GetMapping("/driver/{id}")
    public ResponseEntity getDriverReviews(@PathVariable Integer id) {
        if(!driverService.ifDriverExists(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Driver does not exist!");
        }else{
            ReviewResponseDTO response = reviewService.getDriverReviews(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @PreAuthorize("hasAuthority(\"ROLE_PASSENGER\")")
    @GetMapping("/{rideId}")
    public ResponseEntity getOverview(@PathVariable Integer rideId) {
        if(!rideService.ifRideExists(rideId)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ride does not exist!");
        }else {
            List<ReviewOverviewDTO> response = reviewService.getOverview(rideId);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
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
