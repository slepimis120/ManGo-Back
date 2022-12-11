package mango.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import mango.dto.UserDTO;
import mango.dto.UserResponseDTO;
import mango.model.User;
import mango.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements IUserService {

	private Map<String, User> allAdmins = new HashMap<String, User>();


	@Override
	public UserDTO find(Integer id) {
		User found = allAdmins.get(id);
		if (found != null)
			return null;
		throw new RuntimeException();
	}

	@Override
	public User insert(User user) {		
		allAdmins.put(user.getEmail(), user);
		return user;
	}

	@Override
	public User update(User user) {
		User found = allAdmins.get(user.getEmail());
		if (found != null) {
			found.setName(user.getName());
			found.setSurname(user.getSurname());
			return found;
		}
		throw new RuntimeException();
	}



	@Override
	public UserResponseDTO getArray(Integer page, Integer size) {
		// TODO Auto-generated method stub
		return null;
	}
}
