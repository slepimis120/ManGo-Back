package mango.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import mango.dto.ExpandedUserDTO;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User{

    public Admin(Integer id, String firstName, String lastName, String profilePictureURL, String phoneNumber, String email, String address, String password, boolean blocked) {
        super(id, firstName, lastName, profilePictureURL, phoneNumber, email, address, password, blocked);
    }

    public Admin(int id) {
        super(id);
    }

    public Admin(ExpandedUserDTO data) {
        super(data);
    }

    public Admin() {
    }
}
