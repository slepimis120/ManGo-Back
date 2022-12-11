package mango.dto;

import mango.model.User;

public class UserDTO {

	private Integer id;
	private String name;
	private String surname;
	private String profilePicture;
	private String telephoneNumber;
	private String email;
	private String address;

    public UserDTO (Integer id, String name, String surname, String profilePicture, 
    		String telephoneNumber, String email, String address){
    	this.id = id;
    	this.name = name;
    	this.surname = surname;
    	this.profilePicture = profilePicture;
    	this.telephoneNumber = telephoneNumber;
    	this.email = email;
    	this.address = address;
    }

}