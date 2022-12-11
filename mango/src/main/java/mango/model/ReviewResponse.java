package mango.model;

import java.util.Collection;

public class ReviewResponse {

    private Collection<Review> results;

    private Integer totalCount;

    public ReviewResponse(Collection<Review> results, Integer totalCount){
        this.results = results;
        this.totalCount = totalCount;
    }

    public ReviewResponse(){}

    public Collection<Review> getResults() {
        return results;
    }

    public void setResults(Collection<Review> results) {
        this.results = results;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
