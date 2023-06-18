package com.mango.dto;

import java.util.Date;

public class ReportCounterDTO {

    Date date;
    Float count;

    public ReportCounterDTO(Date date, Float count) {
        this.date = date;
        this.count = count;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getCount() {
        return count;
    }

    public void setCount(Float count) {
        this.count = count;
    }
}
