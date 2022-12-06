package mango.service;

import mango.model.Ride;
import mango.model.Status;
import mango.service.interfaces.IRideService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class RideService implements IRideService {
    private Map<Integer, Ride> allRides = new HashMap<Integer, Ride>();

    @Override
    public Collection<Ride> getAll() {
        return this.allRides.values();
    }

    @Override
    public Ride find(Integer rideId) {
        Ride found = allRides.get(rideId);
        if (found != null)
            return allRides.get(rideId);
        throw new RuntimeException();
    }

    @Override
    public Ride findByDriver(Integer driverId) {
        for(int i = 0; i < allRides.size(); i++){
            if(allRides.get(i).getDriver().getId().equals(driverId) && allRides.get(i).getEndTime().equals(null)){
                return allRides.get(i);
            }
        }
        return null;
    }

    @Override
    public Ride findByPassenger(Integer passengerId) {
        for(int i = 0; i < allRides.size(); i++){
            for(int j = 0; j < allRides.get(i).getPassengers().size(); j++){
                if(allRides.get(i).getPassengers().get(j).getId().equals(passengerId)  && allRides.get(i).getEndTime().equals(null)) {
                    return allRides.get(i);
                }
            }
        }
        return null;
    }

    @Override
    public Ride insert(Ride ride) {
        int size = allRides.size();
        ride.setRideId(size + 1);
        ride.setStatus(Status.pending);
        allRides.put(ride.getRideId(), ride);
        return ride;
    }

    @Override
    public void cancelByPassenger(Integer rideId) {

    }

    @Override
    public Ride accept(Integer rideId) {
        return null;
    }

    @Override
    public Ride end(Integer rideId) {
        return null;
    }

    @Override
    public Ride cancelByDriver(Integer rideId, String reason) {
        return null;
    }

    @Override
    public Ride delete(Integer rideId) {
        return null;
    }

    @Override
    public void deleteAll() {

    }

}
