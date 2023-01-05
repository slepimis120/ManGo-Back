package mango.model;

import java.util.ArrayList;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import mango.dto.ExpandedUserDTO;

@Entity
@DiscriminatorValue("DRIVER")
public class Driver extends User{

	@OneToOne(mappedBy = "driverId")
	private Vehicle vehicle;

	@OneToOne(mappedBy = "driverId")
	private DriverDocument driverDocument;

	@OneToMany(mappedBy = "driver")
	private ArrayList <Ride> rides;

	@OneToMany(mappedBy = "driver")
	private ArrayList <WorkHour> workHours;

	public Driver(Integer id, String firstName, String lastName, String profilePictureURL, String phoneNumber, String email,
			String address, String password, boolean blocked) {
		super(id, firstName, lastName, profilePictureURL, phoneNumber, email, address, password, blocked);
	}

	public Driver(User user){
		super(user.getId(), user.getName(), user.getSurname(), user.getProfilePicture(), user.getTelephoneNumber(), user.getEmail(), user.getAddress(), user.getPassword(), user.isBlocked());
	}
	
	public Driver(ExpandedUserDTO data) {
		super(data);
	}

	public Driver(){}
	
	
	
}
