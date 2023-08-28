package com.mango.dto;

public class PagingDTO {
    private Integer page;
    private Integer size;
    private String sort;
    private String from;
    private String to;



    public PagingDTO(Integer page, Integer size, String sort, String from, String to){
        this.page = page;
        this.size = size;
        this.sort = sort;
        this.from = from;
        this.to = to;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}