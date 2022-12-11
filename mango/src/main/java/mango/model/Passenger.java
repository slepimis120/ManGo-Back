package mango.model;

import java.util.ArrayList;

public class Passenger extends User{
	
	private ArrayList <Ride> rides;

	public Passenger(String firstName, String lastName, String profilePictureURL, String phoneNumber, String email,
			String address, String password, boolean blocked) {
		super(firstName, lastName, profilePictureURL, phoneNumber, email, address, password, blocked);
		this.rides = new ArrayList<Ride>();
	}

	

	
	

}
