package mango.model;

import java.util.List;

import jakarta.persistence.*;
import mango.dto.ExpandedUserDTO;

@Entity
@DiscriminatorValue("PASSENGER")
public class Passenger extends User{

	@ManyToMany(mappedBy = "passengers")
	private List<Ride> rides;

	@OneToMany(mappedBy = "passengers")
	private List <Review> reviews;

	public Passenger(Integer id, String firstName, String lastName, String profilePictureURL, String phoneNumber, String email,
			String address, String password, boolean blocked) {
		super(id, firstName, lastName, profilePictureURL, phoneNumber, email, address, password, blocked);
	}

	public Passenger(User user){
		super(user.getId(), user.getName(), user.getSurname(), user.getProfilePicture(), user.getTelephoneNumber(), user.getEmail(), user.getAddress(), user.getPassword(), user.isBlocked());
	}
	
	public Passenger(ExpandedUserDTO data) {
		super(data);
	}

	public String getName() {
		return super.getName();
	}

	public Passenger(){}

	

	
	

}
