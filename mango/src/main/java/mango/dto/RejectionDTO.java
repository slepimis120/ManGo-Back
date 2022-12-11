package mango.dto;

import java.util.Date;

public class RejectionDTO {

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
