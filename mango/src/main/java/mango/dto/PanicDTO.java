package mango.dto;

import mango.model.Ride;
import mango.model.User;

import java.util.Date;

public class PanicDTO {
    private String reason;

    public PanicDTO(String reason) {
        this.reason = reason;
    }

    public PanicDTO(){}


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
