package mango.service.interfaces;

import mango.model.Location;
import mango.model.Vehicle;

public interface IVehicleService {

    public Vehicle find(Integer vehicleId);

    public Vehicle insert(Vehicle vehicle);

    public Vehicle update(Vehicle vehicle);

    public void updateLocation(Location location, Integer id);

    public void delete(Integer id);

    public void deleteAll();
}
