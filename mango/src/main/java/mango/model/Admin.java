package mango.model;

public class Admin extends User{
	private String username;

	
	public Admin(String firstName, String lastName, String profilePictureURL, String phoneNumber, String email,
			String address, String password, boolean blocked, String username) {
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
