package mango.service;

import mango.dto.GetFavoriteLocationsDTO;
import mango.dto.SendFavoriteLocationsDTO;
import mango.model.*;
import mango.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class RideService{

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private PanicRepository panicRepository;
    @Autowired
    private FavoriteLocationRepository favoriteLocationRepository;


    public Ride insert(Ride ride) {
        ride.setStatus(Ride.Status.pending);
        Date date = new Date();
        ride.setStartTime(date);
        for(RideLocation location: ride.getLocations()){
            location.setRide(ride);
        }
        save(ride);
        return ride;
    }

    public Ride cancelByPassenger(Integer rideId) {
        Ride ride = findById(rideId);
        ride.setStatus(Ride.Status.cancelled);
        save(ride);
        return ride;
    }

    public Ride accept(Integer rideId) {
        Ride ride = findById(rideId);
        ride.setStatus(Ride.Status.accepted);
        Date date = new Date();
        ride.setStartTime(date);
        save(ride);
        return ride;
    }

    public Ride end(Integer rideId) {
        Ride ride = findById(rideId);
        Date date = new Date();
        ride.setEndTime(date);
        ride.setStatus(Ride.Status.finished);
        if(ride.getStartTime() != null) {
            long diffInMillies = Math.abs(date.getTime() - ride.getStartTime().getTime());
            Integer diff = (int) TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
            ride.setEstimatedTimeInMinutes(diff);
        }
        save(ride);
        return ride;
    }

    public Ride cancelByDriver(Integer rideId, Rejection rejection) {
        Date date = new Date();
        rejection.setTimeOfRejection(date);
        Ride ride = findById(rideId);
        ride.setStatus(Ride.Status.cancelled);
        ride.setRejection(rejection);
        save(ride);
        return ride;
    }

    public Panic panic(Integer rideId, Panic panic) {
        Ride ride = findById(rideId);
        panic.setRide(ride);
        panic.setUser(ride.getPassengers().get(0));
        Date date = new Date();
        panic.setTime(date);
        panicRepository.save(panic);
        return panic;
    }

    public FavoriteLocations addFavoriteLocations(GetFavoriteLocationsDTO getFavoriteLocationsDTO){
        return saveFavoriteLocations(new FavoriteLocations(getFavoriteLocationsDTO));
    }

    public List<SendFavoriteLocationsDTO> getFavoriteLocations(Integer id){
        List<SendFavoriteLocationsDTO> sendFavoriteLocationsDTOList = new ArrayList<>();
        for(FavoriteLocations favoritelocations : favoriteLocationRepository.getByUserId(id)){
            sendFavoriteLocationsDTOList.add(new SendFavoriteLocationsDTO(favoritelocations));
        }
        return sendFavoriteLocationsDTOList;
    }

    public FavoriteLocations deleteFavoriteLocations(Integer id){
        return null;
    }

    public Ride save(Ride ride) {
        return rideRepository.save(ride);
    }

    public boolean ifRideExists(Integer id){
        return rideRepository.findById(id).orElse(null) != null;
    }

    public Ride findById(Integer id){
        return rideRepository.findById(id).orElse(null);
    }

    public Ride findActiveByDriver(Integer passengerId){
        return rideRepository.findActiveByDriver(passengerId);
    }

    public Ride findActiveByPassenger(Integer passengerId){
        return rideRepository.findActiveByPassenger(passengerId);
    }

    public Integer getTotalCount(Integer id){
        return favoriteLocationRepository.getTotalCount(id);
    }

    public FavoriteLocations saveFavoriteLocations(FavoriteLocations favoriteLocations){
        return favoriteLocationRepository.save(favoriteLocations);
    }
}
