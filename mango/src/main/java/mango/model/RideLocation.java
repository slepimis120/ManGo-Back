package mango.model;

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

    @ManyToOne
    @JoinColumn(name = "RIDEID",  referencedColumnName = "id")
    private Ride ride;
}
