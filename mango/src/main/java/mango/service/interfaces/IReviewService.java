package mango.service.interfaces;

import mango.dto.ReviewResponseDTO;
import mango.model.Review;
import mango.model.ReviewOverview;

public interface IReviewService {

    public Review sendVehicleReview(Integer vehicleId, Integer rideId, Review review);

    public ReviewResponseDTO getVehicleReviews(Integer vehicleId);

    public Review sendDriverReview(Integer driverId, Integer rideId, Review review);

    public ReviewResponseDTO getDriverReviews(Integer vehicleId);

    public ReviewOverview getOverview(Integer rideId);
}
