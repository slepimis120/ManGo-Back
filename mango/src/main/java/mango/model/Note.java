package mango.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
	private User user;
}
