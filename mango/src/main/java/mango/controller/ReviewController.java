package mango.controller;

import mango.dto.ReviewDTO;
import mango.dto.ReviewResponseDTO;
import mango.mapper.ReviewDTOMapper;
import mango.model.Review;
import mango.model.ReviewOverview;
import mango.service.interfaces.IReviewService;
import mango.service.interfaces.IRideService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    IReviewService service;

    @PostMapping("/{rideId}/vehicle/{id}")
    public ResponseEntity sendVehicleReview(@RequestBody ReviewDTO review, @PathVariable Integer id, Integer rideId) {
        ReviewDTOMapper mapper = new ReviewDTOMapper(new ModelMapper());
        Review newReview = mapper.fromDTOtoReview(review);
        Review response = service.sendVehicleReview(id, rideId, newReview);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/vehicle/{id}")
    public ResponseEntity getVehicleReviews(@PathVariable Integer id) {
        ReviewResponseDTO response = service.getVehicleReviews(id);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @PostMapping("/{rideId}/driver/{id}")
    public ResponseEntity sendDriverReview(@RequestBody ReviewDTO review, @PathVariable Integer id, Integer rideId) {
        ReviewDTOMapper mapper = new ReviewDTOMapper(new ModelMapper());
        Review newReview = mapper.fromDTOtoReview(review);
        Review response = service.sendDriverReview(id, rideId, newReview);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/driver/{id}")
    public ResponseEntity getDriverReviews(@PathVariable Integer id) {
        ReviewResponseDTO response = service.getDriverReviews(id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/{rideId}")
    public ResponseEntity getOverview(@PathVariable Integer rideId) {
        ReviewOverview response = service.getOverview(rideId);
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
