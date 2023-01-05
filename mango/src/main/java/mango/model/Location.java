package mango.model;

import jakarta.persistence.*;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "LATITUDE", nullable = false)
    private Float latitude;

    @Column(name = "LONGITUDE", nullable = false)
    private Float longitude;

    public Location(String address, Float latitude, Float longitude){
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location(){}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }
}
