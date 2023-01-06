package mango.service;

import mango.model.Location;
import mango.model.Vehicle;
import mango.repository.LocationRepository;
import mango.repository.VehicleRepository;
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
        return vehicleRepository.findById(id).orElseGet(null);
    }

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void insertNewLocation(Location location){
        locationRepository.save(location);
    }
}
