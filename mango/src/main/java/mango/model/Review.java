package mango.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "RIDEID",  referencedColumnName = "id")
    private Ride ride;

    @Column(name = "RATING", nullable = false)
    private Integer rating;

    @Column(name = "COMMENT", nullable = false)
    private String comment;

    @Column(name = "REVIEWTYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Review.Type reviewType;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "PASSENGERID",  referencedColumnName = "id")
    private Passenger passengers;

    public Review(Integer ride, Integer rating, String comment, Type reviewType) {
        this.ride = new Ride();
        this.ride.setId(ride);
        this.rating = rating;
        this.comment = comment;
        this.reviewType = reviewType;
    }

    public Review(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Integer id) {
        this.ride = new Ride();
        this.ride.setId(id);
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

    public Type getReviewType() {
        return reviewType;
    }

    public void setReviewType(Type reviewType) {
        this.reviewType = reviewType;
    }

    public Passenger getPassengers() {
        return passengers;
    }

    public void setPassengers(Passenger passengers) {
        this.passengers = passengers;
    }

    public enum Type{
        VEHICLE, DRIVER
    }
}
