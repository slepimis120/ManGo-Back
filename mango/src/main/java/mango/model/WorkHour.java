package mango.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class WorkHour {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "START", nullable = false)
	private LocalDateTime start;

	@Column(name = "END", nullable = false)
	private LocalDateTime end;

	@ManyToOne
	@JoinColumn(name = "DRIVERID",  referencedColumnName = "id")
	private Driver driver;

	public WorkHour(Integer id, LocalDateTime start, LocalDateTime end, Integer driverId) {
		super();
		this.id = id;
		this.start = start;
		this.end = end;
		this.driver = new Driver();
		this.driver.setId(driverId);
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
		return driver.getId();
	}
	public void setDriverId(Integer driverId) {
		this.driver.setId(driverId);
	}
	
	
}
