package mango.service;

import mango.model.RideEstimates;
import mango.model.RideLocation;
import mango.model.Vehicle;
import mango.service.interfaces.IUndregisteredUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UnregisteredUserService implements IUndregisteredUserService {

    @Override
    public RideEstimates rideEstimate(ArrayList<RideLocation> locations, Vehicle.Type type, boolean babyTransport, boolean petTransport) {
        RideEstimates estimates = new RideEstimates(10, 450);
        return estimates;
    }
}
