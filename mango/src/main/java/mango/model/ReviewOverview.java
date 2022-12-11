package mango.model;

public class ReviewOverview {

    private Review vehicleReview;

    private Review driverReview;

    public ReviewOverview(Review vehicleReview, Review driverReview){
        this.vehicleReview = vehicleReview;
        this.driverReview = driverReview;
    }

    public ReviewOverview(){

    }


    public Review getVehicleReview() {
        return vehicleReview;
    }

    public void setVehicleReview(Review vehicleReview) {
        this.vehicleReview = vehicleReview;
    }

    public Review getDriverReview() {
        return driverReview;
    }

    public void setDriverReview(Review driverReview) {
        this.driverReview = driverReview;
    }
}
