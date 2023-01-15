package mango.dto;

import java.util.Date;

public class RejectionResponseDTO {

    private String reason;

    private Date timeOfRejection;

    public RejectionResponseDTO(String reason, Date timeOfRejection){
        this.reason = reason;
        this.timeOfRejection = timeOfRejection;
    }

    public RejectionResponseDTO(){}

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getTimeOfRejection() {
        return timeOfRejection;
    }

    public void setTimeOfRejection(Date timeOfRejection) {
        this.timeOfRejection = timeOfRejection;
    }
}
