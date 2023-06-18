package com.mango.dto;

import java.util.List;

public class ReviewResponseDTO {

    private List<ReviewDTO> results;

    private Integer totalCount;

    public ReviewResponseDTO(List<ReviewDTO> results, Integer totalCount){
        this.results = results;
        this.totalCount = totalCount;
    }

    public ReviewResponseDTO(){
        this.results = null;
        this.totalCount = 0;
    }

    public List<ReviewDTO> getResults() {
        return results;
    }

    public void setResults(List<ReviewDTO> results) {
        this.results = results;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

}
