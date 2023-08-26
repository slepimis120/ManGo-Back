package com.mango.service.interfaces;

import com.mango.dto.CalculatedRideEstimatesDTO;
import com.mango.model.RideLocation;
import com.mango.model.Vehicle;

import java.util.List;

public interface IUndregisteredUserService {

    public CalculatedRideEstimatesDTO rideEstimate(List<RideLocation> locations, Vehicle.Type type, boolean babyTransport, boolean petTransport);

}
