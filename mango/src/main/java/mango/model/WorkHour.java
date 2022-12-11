package mango.model;

import java.time.LocalDateTime;

public class WorkHour {
	private Integer id;
	private LocalDateTime start;
	private LocalDateTime end;
	private Integer driverId;
	public WorkHour(Integer id, LocalDateTime start, LocalDateTime end, Integer driverId) {
		super();
		this.id = id;
		this.start = start;
		this.end = end;
		this.driverId = driverId;
	}
	public WorkHour() {
		super();
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
	public Integer getDriverId() {
		return driverId;
	}
	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}
	
	
}
