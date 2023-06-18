package com.mango.repository;

import com.mango.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query("select r from Review r where (r.reviewType = 'VEHICLE')")
    List<Review> findAllVehicles();

    @Query("select r from Review r where (r.reviewType = 'DRIVER')")
    List<Review> findAllDrivers();

    @Query("select r from Review r where (r.reviewType = 'VEHICLE') and (r.ride.id = :rideId)")
    List<Review> findVehicleReviewByRideId(Integer rideId);

    @Query("select r from Review r where (r.reviewType = 'DRIVER') and (r.ride.id = :rideId)")
    List<Review> findDriverReviewByRideId(Integer rideId);

    @Query("select r from Review r where (r.passengers.id = :passengerId) and (r.ride.id = :rideId) and (r.reviewType = :type)")
    Review ifExists(Integer passengerId, Integer rideId, Review.Type type);
}
