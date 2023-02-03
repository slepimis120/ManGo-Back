package mango.dto;

import java.util.Date;

public class ReportCounterDTO {

    Date date;

    Integer count;

    public ReportCounterDTO(Date date, Integer count) {
        this.date = date;
        this.count = count;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
