package mango.dto;

import java.time.LocalDateTime;

public class WorkHourDTO {
	private Integer id;
	private String start;
	private String end;

	public WorkHourDTO(Integer id, String start, String end) {
		super();
		this.id = id;
		this.start = start;
		this.end = end;
	}
	public WorkHourDTO() {
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;

	}
	
	
}
