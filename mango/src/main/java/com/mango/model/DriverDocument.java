package com.mango.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

	public DriverDocument(String name, String documentImage, Driver driverId) {
		this.name = name;
		this.documentImage = documentImage;
		this.driverId = driverId;
	}
}
