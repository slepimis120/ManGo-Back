package com.mango.service;

import com.mango.dto.ReviewDTO;
import com.mango.dto.ReviewOverviewDTO;
import com.mango.dto.ReviewResponseDTO;
import com.mango.dto.RidePassengerDTO;
import com.mango.model.Passenger;
import com.mango.model.Review;
import com.mango.repository.PassengerRepository;
import com.mango.repository.ReviewRepository;
import com.mango.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private PassengerRepository passengerRepository;

    public Review sendReview(Integer passengerId, Integer rideId, Review review) {
        Review newReview = new Review(rideId, review.getRating(), review.getComment(), review.getReviewType());
        newReview.setPassengers(findPassengerById(passengerId));
        Review existingReview = ifReviewExists(newReview);
        if(existingReview != null){
            existingReview.setRating(newReview.getRating());
            existingReview.setComment(newReview.getComment());
            insertReview(existingReview);
        }else{
            insertReview(newReview);
        }
        return newReview;
    }

    public ReviewResponseDTO getVehicleReviews(Integer vehicleId) {
        ReviewResponseDTO response = new ReviewResponseDTO();
        List<ReviewDTO> reviewList = new ArrayList<>();
        for (Review review : getVehicleReviewByRideId(vehicleId)) {
            RidePassengerDTO reviewPassenger = new RidePassengerDTO(review.getPassengers().getId(), review.getPassengers().getEmail());
            ReviewDTO insertReview = new ReviewDTO(review.getRide().getId(), review.getRating(), review.getComment(), reviewPassenger);
            reviewList.add(insertReview);
            response.setTotalCount(response.getTotalCount() + 1);
        }
        response.setResults(reviewList);
        return response;
    }

    public ReviewResponseDTO getDriverReviews(Integer driverId) {
        ReviewResponseDTO response = new ReviewResponseDTO();
        List<ReviewDTO> reviewList = new ArrayList<>();
        for (Review review : getDriverReviewByRideId(driverId)) {
            RidePassengerDTO reviewPassenger = new RidePassengerDTO(review.getPassengers().getId(), review.getPassengers().getEmail());
            ReviewDTO insertReview = new ReviewDTO(review.getRide().getId(), review.getRating(), review.getComment(), reviewPassenger);
            reviewList.add(insertReview);
            response.setTotalCount(response.getTotalCount() + 1);
        }
        response.setResults(reviewList);
        return response;
    }

    public List<ReviewOverviewDTO> getOverview(Integer rideId) {
        boolean check;
        List<ReviewOverviewDTO> overview = new ArrayList<>();
        List<Integer> insertedId = new ArrayList<>();
        for (Review driverEntry : getDriverReviewByRideId(rideId)) {
            check = false;
            if(driverEntry.getRide().getId().equals(rideId)){
                for(Review vehicleEntry : getVehicleReviewByRideId(rideId)) {
                    if (vehicleEntry.getRide().getId().equals(rideId) && vehicleEntry.getPassengers().getId().equals(driverEntry.getPassengers().getId())) {
                        RidePassengerDTO reviewPassenger = new RidePassengerDTO(driverEntry.getPassengers().getId(), driverEntry.getPassengers().getEmail());
                        ReviewDTO driverReview = new ReviewDTO(driverEntry.getRide().getId(), driverEntry.getRating(), driverEntry.getComment(), reviewPassenger);
                        ReviewDTO vehicleReview = new ReviewDTO(vehicleEntry.getRide().getId(), vehicleEntry.getRating(), vehicleEntry.getComment(), reviewPassenger);
                        ReviewOverviewDTO entry = new ReviewOverviewDTO(vehicleReview, driverReview);
                        overview.add(entry);
                        insertedId.add(vehicleEntry.getId());
                        check = true;
                    }
                }
                if(!check){
                    RidePassengerDTO reviewPassenger = new RidePassengerDTO(driverEntry.getPassengers().getId(), driverEntry.getPassengers().getEmail());
                    ReviewDTO driverReview = new ReviewDTO(driverEntry.getRide().getId(), driverEntry.getRating(), driverEntry.getComment(), reviewPassenger);
                    ReviewOverviewDTO entry = new ReviewOverviewDTO(driverReview, null);
                    overview.add(entry);
                }
            }
        }

        for(Review vehicleEntry : getVehicleReviewByRideId(rideId)){
            check = false;
            for(Integer ids : insertedId){
                if (Objects.equals(vehicleEntry.getId(), ids)) {
                    check = true;
                    break;
                }
            }
            if(!check){
                RidePassengerDTO reviewPassenger = new RidePassengerDTO(vehicleEntry.getPassengers().getId(), vehicleEntry.getPassengers().getEmail());
                ReviewDTO vehicleReview = new ReviewDTO(vehicleEntry.getRide().getId(), vehicleEntry.getRating(), vehicleEntry.getComment(), reviewPassenger);
                ReviewOverviewDTO entry = new ReviewOverviewDTO(null, vehicleReview);
                overview.add(entry);
            }
        }
        return overview;
    }

    public List<Review> getVehicleReviewByRideId(Integer rideId){
        return reviewRepository.findVehicleReviewByRideId(rideId);
    }

    public List<Review> getDriverReviewByRideId(Integer rideId){
        return reviewRepository.findDriverReviewByRideId(rideId);
    }

    public void insertReview(Review review){
        reviewRepository.save(review);
    }

    public Passenger findPassengerById(Integer id){
        return passengerRepository.findById(id).orElse(null);
    }

    public Review ifReviewExists(Review review){
        return reviewRepository.ifExists(review.getPassengers().getId(), review.getRide().getId(), review.getReviewType());
    }
}
