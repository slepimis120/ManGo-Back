package mango.model;

import jakarta.persistence.*;

@Entity
public class DriverDocument {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "DOCUMENTIMAGE", nullable = false)
	private String documentImage;

	@OneToOne
	@JoinColumn(name = "DRIVERID", referencedColumnName = "id")
	private Driver driverId;
	
	public DriverDocument(Integer id, String name, String documentImage, Driver driver) {
		super();
		this.id = id;
		this.name = name;
		this.documentImage = documentImage;
		this.driverId = driver;
	}
	
	public DriverDocument() {}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDocumentImage() {
		return documentImage;
	}
	public void setDocumentImage(String documentImage) {
		this.documentImage = documentImage;
	}
	public Driver getDriverId() {
		return driverId;
	}
	public void setDriverId(Driver driverId) {
		this.driverId = driverId;
	}
	
	
}
