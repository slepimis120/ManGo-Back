package com.mango.dto;

import com.mango.model.Panic;

import java.util.Collection;

public class PanicResponseDTO {
    private Collection<Panic> results;

    private Integer totalCount;


    public PanicResponseDTO(Collection<Panic> results, Integer totalCount) {
        this.results = results;
        this.totalCount = totalCount;
    }

    public PanicResponseDTO(){}

    public Collection<Panic> getResults() {
        return results;
    }

    public void setResults(Collection<Panic> results) {
        this.results = results;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

}
