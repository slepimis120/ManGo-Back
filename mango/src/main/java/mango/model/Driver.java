package mango.model;

public class Driver extends User{
	private String driversLicence;
	private boolean isActive;
	
	//TODO: add Ride history and Vehicle
	
	
	public Driver(String firstName, String lastName, String profilePictureURL, String phoneNumber, String email,
			String address, String password, boolean blocked, String driversLicence, boolean isActive) {
		super();

		this.driversLicence = driversLicence;
		this.isActive = isActive;
	}


	public String getDriversLicence() {
		return driversLicence;
	}


	public void setDriversLicence(String driversLicence) {
		this.driversLicence = driversLicence;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
	
	
}
