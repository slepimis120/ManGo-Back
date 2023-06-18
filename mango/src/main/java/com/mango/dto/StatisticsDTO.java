package com.mango.dto;

public class StatisticsDTO {

    private Integer cancelledRides;

    private Integer acceptedRides;

    private Integer workTime;

    private Integer earnings;

    public StatisticsDTO(Integer cancelledRides, Integer acceptedRides, Integer workTime, Integer earnings) {
        this.cancelledRides = cancelledRides;
        this.acceptedRides = acceptedRides;
        this.workTime = workTime;
        this.earnings = earnings;
    }

    public Integer getCancelledRides() {
        return cancelledRides;
    }

    public void setCancelledRides(Integer cancelledRides) {
        this.cancelledRides = cancelledRides;
    }

    public Integer getAcceptedRides() {
        return acceptedRides;
    }

    public void setAcceptedRides(Integer acceptedRides) {
        this.acceptedRides = acceptedRides;
    }

    public Integer getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Integer workTime) {
        this.workTime = workTime;
    }

    public Integer getEarnings() {
        return earnings;
    }

    public void setEarnings(Integer earnings) {
        this.earnings = earnings;
    }
}
