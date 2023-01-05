package mango.service;

import mango.dto.ReviewResponseDTO;
import mango.model.*;
import mango.service.interfaces.IReviewService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReviewService implements IReviewService {

    private Map<Integer, Review> allVehicleReviews = new HashMap<Integer, Review>();
    private Map<Integer, Review> allDriverReviews = new HashMap<Integer, Review>();

    @Override
    public Review sendVehicleReview(Integer vehicleId, Integer rideId, Review review) {
        Review newReview = new Review();
        newReview.setComment(review.getComment());
        newReview.setRating(review.getRating());
        newReview.setId(allVehicleReviews.size());
        Passenger passenger = new Passenger();
        passenger.setId(123);
        passenger.setEmail("user@example.com");
        newReview.setPassengers(passenger);
        allVehicleReviews.put(newReview.getId(), newReview);
        return newReview;
    }

    @Override
    public ReviewResponseDTO getVehicleReviews(Integer vehicleId) {
        ReviewResponseDTO response = new ReviewResponseDTO();
        for (Map.Entry<Integer, Review> entry : allVehicleReviews.entrySet()) {
            if(entry.getValue().getId().equals(vehicleId)){
                response.getResults().add(entry.getValue());
                response.setTotalCount(response.getTotalCount() + 1);
            }
        }
        return response;
    }

    @Override
    public Review sendDriverReview(Integer driverId, Integer rideId, Review review) {
        Review newReview = new Review();
        newReview.setComment(review.getComment());
        newReview.setRating(review.getRating());
        newReview.setId(allDriverReviews.size());
        Passenger passenger = new Passenger();
        passenger.setId(123);
        passenger.setEmail("user@example.com");
        newReview.setPassengers(passenger);
        allDriverReviews.put(newReview.getId(), newReview);
        return newReview;
    }

    @Override
    public ReviewResponseDTO getDriverReviews(Integer vehicleId) {
        ReviewResponseDTO response = new ReviewResponseDTO();
        for (Map.Entry<Integer, Review> entry : allDriverReviews.entrySet()) {
            if(entry.getValue().getId().equals(vehicleId)){
                response.getResults().add(entry.getValue());
                response.setTotalCount(response.getTotalCount() + 1);
            }
        }
        return response;
    }

    @Override
    public ReviewOverview getOverview(Integer rideId) {
        ReviewOverview overview = new ReviewOverview();
        for (Map.Entry<Integer, Review> entry : allDriverReviews.entrySet()) {
            if(entry.getValue().getRideId().equals(rideId)){
                overview.setDriverReview(entry.getValue());
            }
        }
        for (Map.Entry<Integer, Review> entry : allVehicleReviews.entrySet()) {
            if(entry.getValue().getRideId().equals(rideId)){
                overview.setVehicleReview(entry.getValue());
            }
        }
        return overview;
    }
}
