package com.mango.service;

import com.mango.model.Location;
import com.mango.model.Vehicle;
import com.mango.repository.LocationRepository;
import com.mango.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private LocationRepository locationRepository;

    public Vehicle findOne(Integer id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    public List<Vehicle> findAll() {
        List<Vehicle> list = vehicleRepository.findAll();
        for(Vehicle vehicle : list){
            Location location = locationRepository.findById(vehicle.getCurrentLocation().getId()).orElse(null);
            vehicle.getCurrentLocation().setLatitude(location.getLatitude());
            vehicle.getCurrentLocation().setLongitude(location.getLongitude());
            vehicle.getCurrentLocation().setAddress(location.getAddress());
        }
        return list;
    }

    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void insertNewLocation(Location location){
        locationRepository.save(location);
    }

    public boolean ifVehicleExists(Integer id){
        return vehicleRepository.findById(id).orElse(null) != null;
    }

    public Vehicle getVehicleByDriverId(Integer id){
        return vehicleRepository.getVehicleByDriverId(id);
    }
}
