package mango.dto;

import java.util.ArrayList;

public class UserMessageResponseDTO {
	private Integer totalCount;
	private ArrayList<UserMessageDTO> messages;
	public UserMessageResponseDTO(Integer totalCount, ArrayList<UserMessageDTO> messages) {
		super();
		this.totalCount = totalCount;
		this.messages = messages;
	}
	
	public UserMessageResponseDTO() {}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public ArrayList<UserMessageDTO> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<UserMessageDTO> messages) {
		this.messages = messages;
	}
	
	

}
