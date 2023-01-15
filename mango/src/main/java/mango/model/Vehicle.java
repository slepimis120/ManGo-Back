package mango.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "DRIVERID", referencedColumnName = "id")
    private Driver driver;

    @Column(name = "VEHICLETYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Type vehicleType;

    @Column(name = "MODEL", nullable = false)
    private String model;

    @Column(name = "LICENSENUMBER", nullable = false)
    private String licenseNumber;

    @OneToOne
    @JoinColumn(name = "CURRENTLOCATION", referencedColumnName = "id")
    private Location currentLocation;

    @Column(name = "PASSENGERSEATS", nullable = false)
    private Integer passengerSeats;

    @Column(name = "BABYTRANSPORT", nullable = false)
    private boolean babyTransport;

    @Column(name = "PETTRANSPORT", nullable = false)
    private boolean petTransport;

    public enum Type{
        STANDARD, LUXURY, VAN
    }
}