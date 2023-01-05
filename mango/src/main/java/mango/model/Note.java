package mango.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "MESSAGE", nullable = false)
	private String message;

	@Column(name = "DATE", nullable = false)
	private LocalDateTime date;

	@ManyToOne
	@JoinColumn(name = "USERID",  referencedColumnName = "id")
	private User userId;

	public Note(Integer id, String message, LocalDateTime date, User userId) {
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
		return userId.getId();
	}

	public void setUserId(Integer userId) {
		this.userId.setId(userId);
	}
	
	
}
