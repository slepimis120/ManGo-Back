package mango.dto;

public class PageDTO {

    private Integer size;

    private Integer page;

    public PageDTO(Integer size, Integer page){
        this.size = size;
        this.page = page;
    }

    public PageDTO(){}

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
