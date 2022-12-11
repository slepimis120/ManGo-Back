package mango.dto;

public class PassengerDTO {

	private String name;
	private String surname;
	private String profilePicture;
	private String telephoneNumber;
	private String email;
	private String address;
	private String password;
	
	public PassengerDTO(String name, String surname, String profilePicture, String telephoneNumber, String email,
			String address, String password) {
		this.name = name;
		this.surname = surname;
		this.profilePicture = profilePicture;
		this.telephoneNumber = telephoneNumber;
		this.email = email;
		this.address = address;
		this.password = password;
	}



}
