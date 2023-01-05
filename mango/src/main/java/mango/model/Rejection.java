package mango.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
public class Rejection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "REASON", nullable = false)
    private String reason;

    @Column(name = "TIMEOFREJECTION", nullable = false)
    private Date timeOfRejection;

    @OneToOne
    @JoinColumn(name = "RIDEID",  referencedColumnName = "id")
    private Ride ride;

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
