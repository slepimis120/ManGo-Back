package com.mango.dto;

import java.util.ArrayList;

public class UserMessageResponseDTO {
	private Integer totalCount;
	private ArrayList<UserMessageDTO> results;
	public UserMessageResponseDTO(Integer totalCount, ArrayList<UserMessageDTO> results) {
		super();
		this.totalCount = totalCount;
		this.results = results;
	}
	
	public UserMessageResponseDTO() {}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public ArrayList<UserMessageDTO> getResults() {
		return results;
	}

	public void setResults(ArrayList<UserMessageDTO> results) {
		this.results = results;
	}
	
	

}
