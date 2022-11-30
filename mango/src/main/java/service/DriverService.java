package service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import model.User;
import service.interfaces.IUserService;

public class DriverService implements IUserService{

	private Map<String, User> allDrivers = new HashMap<String, User>();

	@Override
	public Collection<User> getAll() {
		return this.allDrivers.values();
	}

	@Override
	public User find(String email) {
		User found = allDrivers.get(email);
		if (found != null)
			return allDrivers.get(email);
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
			found.setFirstName(user.getFirstName());
			found.setLastName(user.getLastName());
			return found;
		}
		throw new RuntimeException();
	}

	@Override
	public User delete(String email) {
		User found = allDrivers.get(email);
		if (found != null) {
			allDrivers.remove(email);
			return found;
		}
		throw new RuntimeException();
	}

	@Override
	public void deleteAll() {
		allDrivers.clear();
	}

}
