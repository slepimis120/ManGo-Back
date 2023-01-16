package mango.service;

import mango.dto.GetFavoriteLocationsDTO;
import mango.dto.SendFavoriteLocationsDTO;
import mango.model.*;
import mango.repository.*;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private LocationRepository locationRepository;

    public RideService() throws MalformedURLException {
    }


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

    public void getSuitableDrivers(Ride ride) throws IOException {
        List<Vehicle> vehicles = vehicleRepository.findSuitableVehicles(ride.isBabyTransport(), ride.getPassengers().size(), ride.isPetTransport(), ride.getVehicleType().toString());
        Integer fastestVehicleId = -1;
        Driver driver = null;
        float distance = 0.0f;
        float currentDistance = 0.0f;
        System.out.println(vehicles.size());
        for(Vehicle vehicle : vehicles){
            Location location = locationRepository.findById(vehicle.getCurrentLocation().getId()).orElse(null);
            if(location != null){
                String url = "http://router.project-osrm.org/route/v1/driving/" + location.getLongitude() + "," + location.getLatitude() + ";" + ride.getLocations().get(0).getDeparture().getLongitude() + "," + ride.getLocations().get(0).getDeparture().getLatitude() + "?overview=false";
                URL GETDISTANCEURL = new URL(url);
                URLConnection website = GETDISTANCEURL.openConnection();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                website.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null)
                    currentDistance = Float.parseFloat(inputLine.split("distance\":")[1].split("}")[0]);
                in.close();
            }
            if(fastestVehicleId == -1 || currentDistance < distance){
                fastestVehicleId = vehicle.getId();
                distance = currentDistance;
                driver = vehicle.getDriver();
            }
        }
        ride.setDriver(driver);
        save(ride);
    }

    public Integer getVehicleCount(Ride ride){
        return vehicleRepository.findSuitableVehicles(ride.isBabyTransport(), ride.getPassengers().size(), ride.isPetTransport(), ride.getVehicleType().toString()).size();
    }
}
