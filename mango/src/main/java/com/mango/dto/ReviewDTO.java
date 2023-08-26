package com.mango.dto;

public class ReviewDTO {

    private Integer id;

    private Integer rating;

    private String comment;

    private RidePassengerDTO passenger;

    public ReviewDTO(Integer id, Integer rating, String comment, RidePassengerDTO passenger){
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.passenger = passenger;
    }

    public ReviewDTO(){}

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RidePassengerDTO getPassenger() {
        return passenger;
    }

    public void setPassenger(RidePassengerDTO passenger) {
        this.passenger = passenger;
    }
}
