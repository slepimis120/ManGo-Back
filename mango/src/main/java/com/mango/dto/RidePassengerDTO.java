package com.mango.dto;

import com.mango.model.Passenger;

public class RidePassengerDTO {

    private Integer id;

    private String email;

    public RidePassengerDTO(Integer id, String email){
        this.id = id;
        this.email = email;
    }

    public RidePassengerDTO(){}

    public RidePassengerDTO(Passenger passenger){
        this.id = passenger.getId();
        this.email = passenger.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
