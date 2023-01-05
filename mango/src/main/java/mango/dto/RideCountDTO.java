package mango.dto;

import mango.model.Ride;

import java.util.ArrayList;

public class RideCountDTO {
    private Integer totalCount;

    private ArrayList<Ride> results;

    public RideCountDTO(Integer totalCount, ArrayList<Ride> results){
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

    public ArrayList<Ride> getResults() {
        return results;
    }

    public void setResults(ArrayList<Ride> results) {
        this.results = results;
    }
}