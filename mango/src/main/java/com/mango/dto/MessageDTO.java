package com.mango.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class MessageDTO {

    @NotNull
    @Length(max = 500)
    private String message;

    @NotNull
    private String type;

    @NotNull
    private Integer rideId;


    public MessageDTO(String message, String type, Integer rideId) {
        this.message = message;
        this.type = type;
        this.rideId = rideId;
    }

    public MessageDTO(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getRideId() {
        return rideId;
    }

    public void setRideId(Integer rideId) {
        this.rideId = rideId;
    }
}
