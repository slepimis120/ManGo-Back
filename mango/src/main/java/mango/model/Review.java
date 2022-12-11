package mango.model;

public class Review {

    private Integer id;

    private Integer reviewId;

    private Integer rideId;
    private Integer rating;

    private String comment;

    private Passenger passenger;

    public Review(Integer id, Integer reviewId, Integer rideId, Integer rating, String comment, Passenger passenger){
        this.id = id;
        this.reviewId = reviewId;
        this.rideId = rideId;
        this.rating = rating;
        this.comment = comment;
        this.passenger = passenger;
    }

    public Review(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getRideId() {
        return rideId;
    }

    public void setRideId(Integer rideId) {
        this.rideId = rideId;
    }
}
