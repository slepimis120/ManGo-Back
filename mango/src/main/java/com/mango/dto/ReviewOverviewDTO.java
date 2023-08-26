package com.mango.dto;

public class ReviewOverviewDTO {

    private ReviewDTO vehicleReview;

    private ReviewDTO driverReview;


    public ReviewOverviewDTO(ReviewDTO vehicleReview, ReviewDTO driverReview) {
        this.vehicleReview = vehicleReview;
        this.driverReview = driverReview;
    }

    public ReviewDTO getVehicleReview() {
        return vehicleReview;
    }

    public void setVehicleReview(ReviewDTO vehicleReview) {
        this.vehicleReview = vehicleReview;
    }

    public ReviewDTO getDriverReview() {
        return driverReview;
    }

    public void setDriverReview(ReviewDTO driverReview) {
        this.driverReview = driverReview;
    }
}
