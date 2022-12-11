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
public class DriverService implements IUserService {

	private Map<String, User> allDrivers = new HashMap<String, User>();


	@Override
	public UserDTO find(Integer id) {
		User found = allDrivers.get(id);
		if (found != null)
			return null;
		throw new RuntimeException();
	}

	@Override
	public User insert(User user) {		
		allDrivers.put(user.getEmail(), user);
		return user;
	}

	@Override
	public User update(User user) {
		User found = allDrivers.get(user.getEmail());
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
