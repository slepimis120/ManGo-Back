package mango.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import mango.model.RideLocation;
import mango.model.Vehicle;

import java.util.ArrayList;

public class CalculatedRideEstimatesDTO {

    private int id;

    private Integer estimatedTimeInMinutes;

    private Integer estimatedCost;

    public CalculatedRideEstimatesDTO(Integer estimatedTimeInMinutes, Integer estimatedCost){
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
        this.estimatedCost = estimatedCost;
    }

    public CalculatedRideEstimatesDTO(){}

    public Integer getEstimatedTimeInMinutes() {
        return estimatedTimeInMinutes;
    }

    public void setEstimatedTimeInMinutes(Integer estimatedTimeInMinutes) {
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
    }

    public Integer getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(Integer estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

}
