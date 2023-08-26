package com.mango.dto;

public class NoteDTO {
	private Integer id;
	private String message;
	private String date;
	
	public NoteDTO(Integer id, String message, String date) {
		super();
		this.id = id;
		this.message = message;
		this.date = date;
	}
	
	public NoteDTO() {}
	
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
}
