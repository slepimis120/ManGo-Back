package mango.service;

import mango.dto.CalculatedPriceDTO;
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

    @Override
    public CalculatedPriceDTO calculatePrice(Float distance, Vehicle.Type type) {
        Float price = distance * 120;
        switch (type){
            case LUXURY:
                price += 200;
                break;
            case VAN:
                price += 180;
                break;
            case STANDARD:
                price += 120;
                break;
            default:
                price += 120;
        }
        CalculatedPriceDTO priceDTO = new CalculatedPriceDTO(price);
        return  priceDTO;
    }
}
