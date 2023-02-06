package mango.dto;

public class CalculatedPriceDTO {
    private int id;
    private Float estimatedCost;

    public CalculatedPriceDTO(int id, Float estimatedCost) {
        this.id = id;
        this.estimatedCost = estimatedCost;
    }


    public CalculatedPriceDTO(Float estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public CalculatedPriceDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(Float estimatedCost) {
        this.estimatedCost = estimatedCost;
    }
}
