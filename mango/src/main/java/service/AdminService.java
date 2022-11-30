package service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import model.User;
import service.interfaces.IUserService;

public class AdminService implements IUserService{

	private Map<String, User> allAdmins = new HashMap<String, User>();

	@Override
	public Collection<User> getAll() {
		return this.allAdmins.values();
	}

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
			found.setFirstName(user.getFirstName());
			found.setLastName(user.getLastName());
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
}
