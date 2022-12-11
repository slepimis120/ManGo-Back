package mango.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import mango.dto.UserResponseDTO;
import mango.model.User;
import mango.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements IUserService {

	private Map<String, User> allAdmins = new HashMap<String, User>();


	@Override
	public User find(String email) {
		User found = allAdmins.get(email);
		if (found != null)
			return allAdmins.get(email);
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
	public User delete(String email) {
		User found = allAdmins.get(email);
		if (found != null) {
			allAdmins.remove(email);
			return found;
		}
		throw new RuntimeException();
	}

	@Override
	public void deleteAll() {
		allAdmins.clear();
	}

	@Override
	public UserResponseDTO getArray(Integer page, Integer size) {
		// TODO Auto-generated method stub
		return null;
	}
}
