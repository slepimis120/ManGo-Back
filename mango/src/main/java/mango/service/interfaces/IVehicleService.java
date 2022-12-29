package mango.service.interfaces;

import java.util.ArrayList;

import mango.dto.LocationDTO;
import mango.model.Location;
import mango.model.Vehicle;

public interface IVehicleService {
    public boolean updateLocation(Location location, Integer id);
    public ArrayList<Vehicle> getVehicles();
}
