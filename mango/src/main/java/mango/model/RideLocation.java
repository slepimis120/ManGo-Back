package mango.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RideLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="DEPARTURE", unique=false, nullable=true)
    private Location departure;

    @ManyToOne
    @JoinColumn(name="DESTINATION", unique=false, nullable=true)
    private Location destination;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "RIDEID",  referencedColumnName = "id")
    private Ride ride;
}
