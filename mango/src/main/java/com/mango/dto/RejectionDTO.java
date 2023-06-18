package com.mango.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

public class RejectionDTO {

    @NotNull
    @Length(max = 500)
    private String reason;


    public RejectionDTO(String reason){
        this.reason = reason;
    }

    public RejectionDTO(){}

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
