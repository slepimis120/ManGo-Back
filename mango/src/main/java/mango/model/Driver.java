package mango.model;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.ToString;
import mango.dto.ExpandedUserDTO;

@Entity
@DiscriminatorValue("DRIVER")
public class Driver extends User{

	@OneToOne(mappedBy = "driver")
	@ToString.Exclude
	private Vehicle vehicle;

	@OneToOne(mappedBy = "driverId")
	@ToString.Exclude
	private DriverDocument driverDocument;

	@OneToMany(mappedBy = "driver")
	@ToString.Exclude
	private List<Ride> rides;

	@OneToMany(mappedBy = "driver")
	@ToString.Exclude
	private List<WorkHour> workHours;

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
