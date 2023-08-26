package com.mango.dto;

public class DriverDocumentDTO {
	private Integer id;
	private String name;
	private String documentImage;
	private Integer driverId;
	
	public DriverDocumentDTO(Integer id, String name, String documentImage, Integer driverId) {
		super();
		this.id = id;
		this.name = name;
		this.documentImage = documentImage;
		this.driverId = driverId;
	}
	
	public DriverDocumentDTO() {}
	
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
	public Integer getDriverId() {
		return driverId;
	}
	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}
	
	
	

}