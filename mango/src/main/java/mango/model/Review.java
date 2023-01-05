package mango.model;

import jakarta.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "RIDEID",  referencedColumnName = "id")
    private Ride rideId;

    @Column(name = "RATING", nullable = false)
    private Integer rating;

    @Column(name = "COMMENT", nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "PASSENGERID",  referencedColumnName = "id")
    private Passenger passengers;


    @OneToOne(mappedBy = "vehicleReview")
    private ReviewOverview vehicleReview;

    @OneToOne(mappedBy = "driverReview")
    private ReviewOverview driverReview;

    public Review(Integer id, Ride rideId, Integer rating, String comment, Passenger passengers){
        this.id = id;
        this.rideId = rideId;
        this.rating = rating;
        this.comment = comment;
        this.passengers = passengers;
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

    public Ride getRideId() {
        return rideId;
    }

    public void setRideId(Ride rideId) {
        this.rideId = rideId;
    }

    public Passenger getPassengers() {
        return passengers;
    }

    public void setPassengers(Passenger passengers) {
        this.passengers = passengers;
    }
}
