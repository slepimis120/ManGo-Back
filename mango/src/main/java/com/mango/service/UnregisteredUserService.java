package com.mango.service;

import com.mango.dto.CalculatedRideEstimatesDTO;
import com.mango.model.RideLocation;
import com.mango.model.Vehicle;
import com.mango.service.interfaces.IUndregisteredUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnregisteredUserService implements IUndregisteredUserService {

    @Override
    public CalculatedRideEstimatesDTO rideEstimate(List<RideLocation> locations, Vehicle.Type type, boolean babyTransport, boolean petTransport) {
        CalculatedRideEstimatesDTO estimates = new CalculatedRideEstimatesDTO(10, 450);
        return estimates;
    }
}
