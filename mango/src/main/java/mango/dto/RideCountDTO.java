package mango.dto;

import mango.model.Ride;

import java.util.List;

public class RideCountDTO {
    private Integer totalCount;

    private List<Ride> results;

    public RideCountDTO(Integer totalCount, List<Ride> results){
        this.totalCount = totalCount;
        this.results = results;
    }

    public RideCountDTO(){}


    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<Ride> getResults() {
        return results;
    }

    public void setResults(List<Ride> results) {
        this.results = results;
    }
}