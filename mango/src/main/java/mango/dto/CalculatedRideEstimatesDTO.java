package mango.dto;

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
