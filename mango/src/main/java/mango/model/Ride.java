package mango.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "STARTTIME", nullable = false)
    private Date startTime;

    @Column(name = "ENDTIME", nullable = true)
    private Date endTime;

    @Column(name = "TOTALCOST", nullable = true)
    private Integer totalCost;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "DRIVERID",  referencedColumnName = "id")
    private Driver driver;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Passenger> passengers;

    @Column(name = "ESTIMATEDTIMEINMINUTES", nullable = true)
    private Integer estimatedTimeInMinutes;

    @Column(name = "VEHICLETYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Vehicle.Type vehicleType;

    @Column(name = "BABYTRANSPORT", nullable = false)
    private boolean babyTransport;

    @Column(name = "PETTRANSPORT", nullable = false)
    private boolean petTransport;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @JsonManagedReference
    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER)
    private List<RideLocation> locations;

    @OneToOne(mappedBy = "ride")
    private Rejection rejection;

    @JsonBackReference
    @OneToMany(mappedBy = "ride")
    private List<Panic> panic;

    @JsonBackReference
    @OneToMany(mappedBy = "ride")
    private List<UserMessage> userMessages;

    @JsonBackReference
    @OneToMany(mappedBy = "ride")
    private List<ReviewOverview> reviewOverview;

    public enum Status{pending, accepted, rejected, active, finished, cancelled}


    public Ride(Driver driver, List<RideLocation> locations, List<Passenger> passengers, Vehicle.Type vehicleType, boolean babyTransport, boolean petTransport){
        this.id = 0;
        this.driver = driver;
        this.locations = locations;
        this.passengers = passengers;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.startTime = null;
        this.endTime = null;
        this.totalCost = null;
        this.estimatedTimeInMinutes = null;
        this.status = Status.pending;
    }

    public Ride(){}

    public List<RideLocation> getLocations() {
        return locations;
    }

    public void setLocations(List<RideLocation> locations) {
        this.locations = locations;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Vehicle.Type getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Vehicle.Type vehicleType) {
        this.vehicleType = vehicleType;
    }

    public boolean isBabyTransport() {
        return babyTransport;
    }

    public void setBabyTransport(boolean babyTransport) {
        this.babyTransport = babyTransport;
    }

    public boolean isPetTransport() {
        return petTransport;
    }

    public void setPetTransport(boolean petTransport) {
        this.petTransport = petTransport;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }

    public Integer getEstimatedTimeInMinutes() {
        return estimatedTimeInMinutes;
    }

    public void setEstimatedTimeInMinutes(Integer estimatedTimeInMinutes) {
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int rideId) {
        this.id = rideId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Rejection getRejection() {
        return rejection;
    }

    public void setRejection(Rejection rejection) {
        this.rejection = rejection;
    }
}
