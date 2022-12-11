package mango.model;

import java.util.ArrayList;

import mango.dto.ExpandedUserDTO;

public class Passenger extends User{
	
	private ArrayList <Ride> rides;

	public Passenger(Integer id, String firstName, String lastName, String profilePictureURL, String phoneNumber, String email,
			String address, String password, boolean blocked) {
		super(id, firstName, lastName, profilePictureURL, phoneNumber, email, address, password, blocked);
		this.rides = new ArrayList<Ride>();
	}

	public Passenger(User user){
		super(user.getId(), user.getName(), user.getSurname(), user.getProfilePicture(), user.getTelephoneNumber(), user.getEmail(), user.getAddress(), user.getPassword(), user.isBlocked());
		this.rides = new ArrayList<Ride>();
	}
	
	public Passenger(ExpandedUserDTO data) {
		super(data);
		this.rides = new ArrayList<Ride>();
	}

	public Passenger(){}

	

	
	

}
