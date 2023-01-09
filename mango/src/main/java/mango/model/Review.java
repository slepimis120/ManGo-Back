package mango.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "PASSENGERID",  referencedColumnName = "id")
    private Passenger passengers;


    @OneToOne(mappedBy = "vehicleReview")
    private ReviewOverview vehicleReview;

    @OneToOne(mappedBy = "driverReview")
    private ReviewOverview driverReview;
}
