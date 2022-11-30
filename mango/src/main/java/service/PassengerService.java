package service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import model.User;
import service.interfaces.IUserService;

public class PassengerService implements IUserService{
	
	//TODO: create repository classes after connecting to the database
	private Map<String, User> allPassengers = new HashMap<String, User>();

	@Override
	public Collection<User> getAll() {
		return this.allPassengers.values();
	}

	@Override
	public User find(String email) {
		User found = allPassengers.get(email);
		if (found != null)
			return allPassengers.get(email);
		throw new RuntimeException();
	}

	@Override
	public User insert(User user) {		
		allPassengers.put(user.getEmail(), user);
		return user;
	}

	@Override
	public User update(User user) {
		User found = allPassengers.get(user.getEmail());
		if (found != null) {
			found.setFirstName(user.getFirstName());
			found.setLastName(user.getLastName());
			return found;
		}
		throw new RuntimeException();
	}

	@Override
	public User delete(String email) {
		User found = allPassengers.get(email);
		if (found != null) {
			allPassengers.remove(email);
			return found;
		}
		throw new RuntimeException();
	}

	@Override
	public void deleteAll() {
		allPassengers.clear();
	}

}
