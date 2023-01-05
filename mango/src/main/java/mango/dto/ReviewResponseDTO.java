package mango.dto;

import mango.model.Review;

import java.util.Collection;

public class ReviewResponseDTO {

    private Collection<Review> results;

    private Integer totalCount;

    public ReviewResponseDTO(Collection<Review> results, Integer totalCount){
        this.results = results;
        this.totalCount = totalCount;
    }

    public ReviewResponseDTO(){}

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
