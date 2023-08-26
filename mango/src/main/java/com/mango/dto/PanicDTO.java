package com.mango.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

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
