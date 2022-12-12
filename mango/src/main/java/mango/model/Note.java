package mango.model;

import java.time.LocalDateTime;

public class Note {
	private Integer id;
	private String message;
	private LocalDateTime date;
	private Integer userId;
	public Note(Integer id, String message, LocalDateTime date, Integer userId) {
		super();
		this.id = id;
		this.message = message;
		this.date = date;
		this.userId = userId;
	}
	
	public Note() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
}
