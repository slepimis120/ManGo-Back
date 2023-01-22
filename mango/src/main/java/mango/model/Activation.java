package mango.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Activation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "PASSENGERID",  referencedColumnName = "id")
    private Passenger passengerId;

    @Column(name = "SENDDATE", nullable = false)
    private Date activationSendDate;

    @Column(name = "ISACTIVATED", nullable = false)
    private boolean isActivated;

    public Activation(Passenger passengerId, Date activationSendDate, boolean isActivated) {
        this.passengerId = passengerId;
        this.activationSendDate = activationSendDate;
        this.isActivated = isActivated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Passenger getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Passenger passengerId) {
        this.passengerId = passengerId;
    }

    public Date getActivationSendDate() {
        return activationSendDate;
    }

    public void setActivationSendDate(Date activationSendDate) {
        this.activationSendDate = activationSendDate;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }
}
