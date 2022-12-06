package mango.service.interfaces;

import mango.model.Ride;

import java.util.Collection;

public interface IRideService {

    public Collection<Ride> getAll();

    public Ride find(Integer rideId);
    public Ride findByDriver(Integer driverId);
    public Ride findByPassenger(Integer passengerId);
    public Ride insert(Ride ride);
    public void cancelByPassenger(Integer rideId);
    public Ride accept(Integer rideId);
    public Ride end(Integer rideId);
    public Ride cancelByDriver(Integer rideId, String reason);
    public Ride delete(Integer rideId);
    public void deleteAll();
}
