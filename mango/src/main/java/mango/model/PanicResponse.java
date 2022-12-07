package mango.model;

import java.util.Collection;

public class PanicResponse {
    private Collection<Panic> results;

    private Integer totalCount;


    public PanicResponse(Collection<Panic> results, Integer totalCount) {
        this.results = results;
        this.totalCount = totalCount;
    }

    public PanicResponse(){}

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
