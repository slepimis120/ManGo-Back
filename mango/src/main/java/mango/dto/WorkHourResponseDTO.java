package mango.dto;

import java.util.ArrayList;

import mango.model.WorkHour;

public class WorkHourResponseDTO {
	private ArrayList<WorkHourDTO> workHoursDTO;
	private Integer totalCout;
	public WorkHourResponseDTO(ArrayList<WorkHourDTO> workHoursDTO, Integer totalCout) {
		super();
		this.workHoursDTO = workHoursDTO;
		this.totalCout = totalCout;
	}
		
	public WorkHourResponseDTO() {}

	public ArrayList<WorkHourDTO> getWorkHoursDTO() {
		return workHoursDTO;
	}

	public void setWorkHoursDTO(ArrayList<WorkHourDTO> workHoursDTO) {
		this.workHoursDTO = workHoursDTO;
	}

	public Integer getTotalCout() {
		return totalCout;
	}

	public void setTotalCout(Integer totalCout) {
		this.totalCout = totalCout;
	}


	
}
