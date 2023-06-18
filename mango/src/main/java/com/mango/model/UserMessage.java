package com.mango.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class UserMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "TIMEOFSENDING", nullable = false)
	private LocalDateTime timeOfSending;

	@ManyToOne
	@JoinColumn(name = "SENDERID",  referencedColumnName = "id")
	private User sender;

	@ManyToOne
	@JoinColumn(name = "RECEIVERID",  referencedColumnName = "id")
	private User receiver;

	@Column(name = "MESSAGE", nullable = false)
	private String message;
	@Column(name = "TYPE", nullable = false)
	@Enumerated(EnumType.STRING)
	private Type type;

	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "RIDEID",  referencedColumnName = "id")
	private Ride ride;

	public enum Type{
		SUPPORT, RIDE, PANIC
	}

	public UserMessage(Integer id, LocalDateTime timeOfSending, Integer senderId, Integer receiverId, String message,
			Type type, Integer ride) {
		super();
		this.id = id;
		this.timeOfSending = timeOfSending;
		this.sender.setId(senderId);
		this.receiver.setId(receiverId);
		this.message = message;
		this.type = type;
		this.ride.setId(ride);
	}
	public UserMessage() {
	}

	public UserMessage(LocalDateTime timeOfSending, User sender, User receiver, String message, Type type, Ride ride) {
		this.timeOfSending = timeOfSending;
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
		this.type = type;
		this.ride = ride;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDateTime getTimeOfSending() {
		return timeOfSending;
	}
	public void setTimeOfSending(LocalDateTime timeOfSending) {
		this.timeOfSending = timeOfSending;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public User getReceiver() {
		return receiver;
	}
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Ride getRide() {
		return ride;
	}
	public void setRide(Ride ride) {
		this.ride = ride;
	}
	
	
}
