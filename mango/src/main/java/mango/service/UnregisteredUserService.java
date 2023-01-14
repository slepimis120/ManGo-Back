package mango.service;

import mango.dto.CalculatedRideEstimatesDTO;
import mango.model.RideLocation;
import mango.model.Vehicle;
import mango.service.interfaces.IUndregisteredUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnregisteredUserService implements IUndregisteredUserService {

    @Override
    public CalculatedRideEstimatesDTO rideEstimate(List<RideLocation> locations, Vehicle.Type type, boolean babyTransport, boolean petTransport) {
        CalculatedRideEstimatesDTO estimates = new CalculatedRideEstimatesDTO(10, 450);
        return estimates;
    }
}
