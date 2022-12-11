package mango.model;

import java.util.ArrayList;

public class RideCount {
    private Integer totalCount;

    private ArrayList<Ride> results;

    public RideCount(Integer totalCount, ArrayList<Ride> results){
        this.totalCount = totalCount;
        this.results = results;
    }

    public RideCount(){}


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
