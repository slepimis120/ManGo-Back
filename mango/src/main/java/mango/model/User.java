package mango.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import mango.dto.ExpandedUserDTO;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "USERS")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "SURNAME", nullable = false)
	private String surname;

	@Column(name = "PROFILEPICTURE", nullable = false)
	private String profilePicture;

	@Column(name = "TELEPHONENUMBER", nullable = false)
	private String telephoneNumber;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Column(name = "ADDRESS", nullable = false)
	private String address;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "BLOCKED", nullable = false)
	private boolean blocked;

	@JsonBackReference
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "user")
	private List<Note> note;

	@JsonBackReference
	@OneToMany(mappedBy = "user")
	private List<Panic> panic;
	
	public User(Integer id, String firstName, String lastName, String profilePictureURL, String phoneNumber, String email,
				String address, String password, boolean blocked) {
		this.id = id;
		this.name = firstName;
		this.surname = lastName;
		this.profilePicture = profilePictureURL;
		this.telephoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.password = password;
		this.blocked = blocked;
	}

	public User(int id){
		this.id = id;
	}
	public User(ExpandedUserDTO data) {
		this.id = -1;
		this.name = data.getName();
		this.surname = data.getSurname();
		this.profilePicture = data.getProfilePicture();
		this.telephoneNumber = data.getTelephoneNumber();
		this.email = data.getEmail();
		this.address = data.getAddress();
		this.password = data.getPassword();
		this.blocked = false;
	}
	
    public User() {

    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public List<Note> getNote(){return note;}

	public void setNote(List<Note> note){this.note = note;}

	@Transient
	public String getDecriminatorValue() {
		return this.getClass().getAnnotation(DiscriminatorValue.class).value();
	}
}
