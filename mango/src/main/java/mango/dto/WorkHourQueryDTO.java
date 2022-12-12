package mango.dto;

public class WorkHourQueryDTO {

    private Integer page;

    private Integer size;
    private String from;
    private String to;

    public WorkHourQueryDTO(Integer page, Integer size, String from, String to){
        this.page = page;
        this.size = size;
        this.from = from;
        this.to = to;
    }

    public WorkHourQueryDTO(){}

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
