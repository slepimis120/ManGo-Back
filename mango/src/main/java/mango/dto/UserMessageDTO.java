package mango.dto;

import mango.model.UserMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserMessageDTO {
	private Integer id;
	private String timeOfSending;
	private Integer senderId;
	private Integer receiverId;
	private String message;
	private String type;
	private Integer rideId;
	public UserMessageDTO(Integer id, String timeOfSending, Integer senderId, Integer receiverId, String message,
			String type, Integer rideId) {
		super();
		this.id = id;
		this.timeOfSending = timeOfSending;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.message = message;
		this.type = type;
		this.rideId = rideId;
	}

	public  UserMessageDTO(UserMessage message){
		super();
		this.id = message.getId();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
		String dateFormated = message.getTimeOfSending().format(format);
		this.timeOfSending = dateFormated;
		this.senderId = message.getSender().getId();
		this.receiverId = message.getReceiver().getId();
		this.message = message.getMessage();
		this.rideId = message.getRide().getId();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTimeOfSending() {
		return timeOfSending;
	}
	public void setTimeOfSending(String timeOfSending) {
		this.timeOfSending = timeOfSending;
	}
	public Integer getSenderId() {
		return senderId;
	}
	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}
	public Integer getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getRideId() {
		return rideId;
	}
	public void setRideId(Integer rideId) {
		this.rideId = rideId;
	}
	
}
