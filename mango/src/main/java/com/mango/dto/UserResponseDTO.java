package com.mango.dto;

import java.util.ArrayList;

public class UserResponseDTO {
	private Integer totalCount;
	private ArrayList<UserDTO> results = new ArrayList<UserDTO>();
	
	public UserResponseDTO(Integer totalCount, ArrayList<UserDTO> results){
		this.totalCount = totalCount;
		this.results = results;
	}

	public UserResponseDTO(){}
	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public ArrayList<UserDTO> getResults() {
		return results;
	}

	public void setResults(ArrayList<UserDTO> results) {
		this.results = results;
	}
}
