package mango.model;

import java.util.ArrayList;

import mango.dto.ExpandedUserDTO;

public class Driver extends User{

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
