package com.mango.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import com.mango.dto.LocationDTO;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonBackReference
    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @JsonBackReference
    @Column(name = "LATITUDE", nullable = false)
    private Float latitude;

    @JsonBackReference
    @Column(name = "LONGITUDE", nullable = false)
    private Float longitude;


    public Location(String address, Float latitude, Float longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location(LocationDTO locationDTO){
        this.address = locationDTO.getAddress();
        this.latitude = locationDTO.getLatitude();
        this.longitude = locationDTO.getLongitude();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Location() {

    }
}
