package mango.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkHour {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "STARTTIME", nullable = false)
	private Date start;

	@Column(name = "ENDTIME", nullable = false)
	private Date end;
	@ManyToOne
	@JoinColumn(name = "DRIVERID",  referencedColumnName = "id")
	private Driver driver;
}
