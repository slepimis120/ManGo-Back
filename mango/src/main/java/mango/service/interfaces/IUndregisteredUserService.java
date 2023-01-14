package mango.service.interfaces;

import mango.dto.CalculatedRideEstimatesDTO;
import mango.model.RideLocation;
import mango.model.Vehicle;

import java.util.List;

public interface IUndregisteredUserService {

    public CalculatedRideEstimatesDTO rideEstimate(List<RideLocation> locations, Vehicle.Type type, boolean babyTransport, boolean petTransport);

}
