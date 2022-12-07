package mango.service;

import mango.model.Location;
import mango.model.Ride;
import mango.model.Vehicle;
import mango.service.interfaces.IRideService;
import mango.service.interfaces.IVehicleService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class VehicleService implements IVehicleService {

    private Map<Integer, Vehicle> allVehicles = new HashMap<Integer, Vehicle>();

    @Override
    public Vehicle find(Integer vehicleId) {
        for (Map.Entry<Integer,Vehicle> entry : allVehicles.entrySet()) {
            if (entry.getValue().getId().equals(vehicleId)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public Vehicle insert(Vehicle vehicle) {
        int size = allVehicles.size();
        vehicle.setId(size + 1);
        allVehicles.put(vehicle.getId(), vehicle);
        return vehicle;
    }

    @Override
    public Vehicle update(Vehicle vehicle) {
        return null;
    }

    @Override
    public void updateLocation(Location location, Integer id) {
        for (Map.Entry<Integer,Vehicle> entry : allVehicles.entrySet()) {
            if (entry.getValue().getId().equals(id)) {
                entry.getValue().setCurrentLocation(location);
            }
        }
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void deleteAll() {

    }
}
