package com.mango.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDTO {
    String start;

    public DateDTO(String start) {
        this.start = start;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }
}
