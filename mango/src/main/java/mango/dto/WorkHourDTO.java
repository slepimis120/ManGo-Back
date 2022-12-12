package mango.dto;

import java.time.LocalDateTime;

public class WorkHourDTO {
	private Integer id;
	private LocalDateTime start;
	private LocalDateTime end;

	public WorkHourDTO(Integer id, LocalDateTime start, LocalDateTime end) {
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
	public LocalDateTime getStart() {
		return start;
	}
	public void setStart(LocalDateTime start) {
		this.start = start;
	}
	public LocalDateTime getEnd() {
		return end;
	}
	public void setEnd(LocalDateTime end) {
		this.end = end;

	}
	
	
}
