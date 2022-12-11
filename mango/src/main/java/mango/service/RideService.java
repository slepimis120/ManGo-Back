package mango.service;

import mango.model.*;
import mango.service.interfaces.IRideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class RideService implements IRideService {
    private Map<Integer, Ride> allRides = new HashMap<Integer, Ride>();
    PanicService panicService;

    @Autowired
    public RideService(PanicService panicService){
        this.panicService = panicService;
    }

    @Override
    public Collection<Ride> getAll() {
        return this.allRides.values();
    }

    @Override
    public Ride find(Integer rideId) {
        Ride found = allRides.get(rideId);
        if (found != null)
            return allRides.get(rideId);
        return null;
    }


    @Override
    public Ride findByDriver(Integer driverId) {
        for (Map.Entry<Integer, Ride> entry : allRides.entrySet()) {
            if (entry.getValue().getDriver() != null && entry.getValue().getDriver().getId().equals(driverId) && entry.getValue().getEndTime() == null) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public Ride findByPassenger(Integer passengerId) {
        for (Map.Entry<Integer, Ride> entry : allRides.entrySet()) {
            for(int j = 0; j < entry.getValue().getPassengers().size(); j++){
                if(entry.getValue().getPassengers() != null && entry.getValue().getPassengers().get(j).getId().equals(passengerId)  && entry.getValue().getEndTime() == null) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public Ride insert(Ride ride) {
        int size = allRides.size();
        ride.setId(size + 1);
        ride.setStatus(Ride.Status.pending);
        allRides.put(ride.getId(), ride);

        Driver driver = new Driver();
        driver.setId(1);
        driver.setEmail("user@example.com");
        ride.setDriver(driver);

        Rejection rejection = new Rejection();
        rejection.setReason("Ride is canceled due to previous problems with the passenger");
        Date date = new Date();
        rejection.setTimeOfRejection(date);
        ride.setRejection(rejection);
        return ride;
    }

    @Override
    public Ride cancelByPassenger(Integer rideId) {
        for (Map.Entry<Integer, Ride> entry : allRides.entrySet()) {
            if(entry.getValue().getId().equals(rideId)){
                entry.getValue().setStatus(Ride.Status.cancelled);
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public Ride accept(Integer rideId) {
        for (Map.Entry<Integer, Ride> entry : allRides.entrySet()) {
            if(entry.getValue().getId().equals(rideId)){
                entry.getValue().setStatus(Ride.Status.accepted);
                Date date = new Date();
                entry.getValue().setStartTime(date);
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public Ride end(Integer rideId) {
        for (Map.Entry<Integer, Ride> entry : allRides.entrySet()) {
            if(entry.getValue().getId().equals(rideId)){
                Date date = new Date();
                entry.getValue().setEndTime(date);
                entry.getValue().setStatus(Ride.Status.finished);
                if(entry.getValue().getStartTime() != null) {
                    long diffInMillies = Math.abs(date.getTime() - entry.getValue().getStartTime().getTime());
                    Integer diff = (int) TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
                    entry.getValue().setEstimatedTimeInMinutes(diff);
                }
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public Ride cancelByDriver(Integer rideId, Rejection rejection) {
        Date date = new Date();
        rejection.setTimeOfRejection(date);
        for (Map.Entry<Integer, Ride> entry : allRides.entrySet()) {
            if (entry.getValue().getId().equals(rideId)) {
                entry.getValue().setStatus(Ride.Status.cancelled);
                entry.getValue().setRejection(rejection);
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public Panic panic(Integer rideId, Panic panic) {
        for (Map.Entry<Integer, Ride> entry : allRides.entrySet()) {
            if (entry.getValue().getId().equals(rideId)) {
                panic.setRide(entry.getValue());
                panic.setUser(entry.getValue().getPassengers().get(0));
                Date date = new Date();
                panic.setTime(date);
                panic.setId(panicService.getAllPanic().size() + 1);
                panicService.getAllPanic().put(panic.getId(), panic);
                return panic;
            }
        }
        return null;
    }
}
