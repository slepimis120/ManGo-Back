package mango.dto;

import java.util.ArrayList;

public class UserResponseDTO {
	private Integer totalCount;
	private ArrayList<UserDTO> results = new ArrayList<UserDTO>();
	
	public UserResponseDTO(Integer totalCount, ArrayList<UserDTO> results){
		this.totalCount = totalCount;
		this.results = results;
	}
}
