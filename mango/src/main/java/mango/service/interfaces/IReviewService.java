package mango.service.interfaces;

import mango.model.Review;
import mango.model.ReviewOverview;
import mango.model.ReviewResponse;

public interface IReviewService {

    public Review sendVehicleReview(Integer vehicleId, Integer rideId, Review review);

    public ReviewResponse getVehicleReviews(Integer vehicleId);

    public Review sendDriverReview(Integer driverId, Integer rideId, Review review);

    public ReviewResponse getDriverReviews(Integer vehicleId);

    public ReviewOverview getOverview(Integer rideId);
}
