package mango.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import mango.model.User;
import mango.service.interfaces.IUserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
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
		int size = allPassengers.size();
		user.setId(size + 1);
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
