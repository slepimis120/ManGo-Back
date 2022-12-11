package mango.service.interfaces;

import mango.model.RideEstimates;
import mango.model.RideLocation;
import mango.model.Vehicle;

import java.util.ArrayList;

public interface IUndregisteredUserService {

    public RideEstimates rideEstimate(ArrayList<RideLocation> locations, Vehicle.Type type, boolean babyTransport, boolean petTransport);

}
