package mango.service;

import mango.dto.LocationDTO;
import mango.model.Location;
import mango.model.Ride;
import mango.model.Ride.Status;
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
    public boolean updateLocation(Location location, Integer id) {
        Vehicle vehicle = new Vehicle();
        int size = allVehicles.size();
        vehicle.setId(size + 1);
        vehicle.setVehicleType(Vehicle.Type.VAN);
        allVehicles.put(vehicle.getId(), vehicle);
        for (Map.Entry<Integer,Vehicle> entry : allVehicles.entrySet()) {
            if (entry.getValue().getId().equals(id)) {
                entry.getValue().setCurrentLocation(location);
                return true;
            }
        }
        return false;
    }

}
