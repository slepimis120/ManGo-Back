package mango.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Rejection {

    private String reason;

    private Date timeOfRejection;

    public Rejection(String reason, Date timeOfRejection){
        this.reason = reason;
        this.timeOfRejection = timeOfRejection;

    }

    public Rejection(){}

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
