package mango.service.interfaces;

import mango.dto.LocationDTO;
import mango.model.Location;
import mango.model.Vehicle;

public interface IVehicleService {
    public boolean updateLocation(Location location, Integer id);
}
