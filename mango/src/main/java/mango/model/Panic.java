package mango.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Panic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "USERID",  referencedColumnName = "id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "RIDEID",  referencedColumnName = "id")
    private Ride ride;

    @Column(name = "TIME", nullable = false)
    private Date time;

    @Column(name = "REASON", nullable = false)
    private String reason;

    public Panic(Integer id, User user, Ride ride, Date time, String reason) {
        this.id = id;
        this.userId = user;
        this.ride = ride;
        this.time = time;
        this.reason = reason;
    }

    public Panic(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return userId;
    }

    public void setUser(User user) {
        this.userId = user;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
