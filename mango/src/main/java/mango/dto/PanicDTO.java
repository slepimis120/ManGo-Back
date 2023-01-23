package mango.dto;

import jakarta.validation.constraints.NotNull;
import mango.model.Ride;
import mango.model.User;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

public class PanicDTO {

    @NotNull
    @Length(max = 500)
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
