package mango.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class ExpandedUserDTO {

	@NotNull
	@Length(max = 100)
	private String name;

	@NotNull
	@Length(max = 100)
	private String surname;

	private String profilePicture;

	@Length(max = 100)
	private String telephoneNumber;

	@NotNull
	@Length(max = 100)
	@Email
	private String email;

	@NotNull
	@Length(max = 100)
	private String address;

	@NotNull
	@Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])(?!.*[^a-zA-Z0-9@#$^+=])(.{8,15})$")
	private String password;
	
	public ExpandedUserDTO(String name, String surname, String profilePicture, String telephoneNumber, String email,
			String address, String password) {
		this.name = name;
		this.surname = surname;
		this.profilePicture = profilePicture;
		this.telephoneNumber = telephoneNumber;
		this.email = email;
		this.address = address;
		this.password = password;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
