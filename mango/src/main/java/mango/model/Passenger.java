package mango.model;

public class Passenger extends User{
	
	//TODO: add Ride history

	public Passenger(String firstName, String lastName, String profilePictureURL, String phoneNumber, String email,
			String address, String password, boolean blocked) {
		super(firstName, lastName, profilePictureURL, phoneNumber, email, address, password, blocked);
	}

	public Passenger() {
		super();
	}
	

	
	

}
