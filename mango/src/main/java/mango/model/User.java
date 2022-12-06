package mango.model;

public abstract class User {
	private Integer id;
	private String firstName;
	private String lastName;
	private String profilePictureURL;
	private String phoneNumber;
	private String email;
	private String Address;
	private String password;
	private boolean blocked;
	
	public User(String firstName, String lastName, String profilePictureURL, String phoneNumber, String email,
				String address, String password, boolean blocked) {
		super();
		this.id = null;
		this.firstName = firstName;
		this.lastName = lastName;
		this.profilePictureURL = profilePictureURL;
		this.phoneNumber = phoneNumber;
		this.email = email;
		Address = address;
		this.password = password;
		this.blocked = blocked;
	}

    public User() {

    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getProfilePictureURL() {
		return profilePictureURL;
	}

	public void setProfilePictureURL(String profilePictureURL) {
		this.profilePictureURL = profilePictureURL;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
