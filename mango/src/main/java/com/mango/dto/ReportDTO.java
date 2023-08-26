package com.mango.dto;

import java.util.ArrayList;
import java.util.List;

public class ReportDTO {

    List<ReportCounterDTO> totalRides;

    List<ReportCounterDTO> totalKilometres;

    public ReportDTO(List<ReportCounterDTO> totalRides, List<ReportCounterDTO> totalKilometres) {
        this.totalRides = totalRides;
        this.totalKilometres = totalKilometres;
    }

    public ReportDTO() {
        this.totalRides = new ArrayList<ReportCounterDTO>();
        this.totalKilometres = new ArrayList<ReportCounterDTO>();
    }

    public List<ReportCounterDTO> getTotalRides() {
        return totalRides;
    }

    public void setTotalRides(List<ReportCounterDTO> totalRides) {
        this.totalRides = totalRides;
    }

    public List<ReportCounterDTO> getTotalKilometres() {
        return totalKilometres;
    }

    public void setTotalKilometres(List<ReportCounterDTO> totalKilometres) {
        this.totalKilometres = totalKilometres;
    }
}
