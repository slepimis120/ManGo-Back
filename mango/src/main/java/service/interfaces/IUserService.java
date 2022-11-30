package service.interfaces;

import java.util.Collection;

import model.User;

public interface IUserService {

	public Collection<User> getAll();
	
	public User find(String email);
	
	public User insert(User user);

	public User update(User user);
	
	public User delete(String email);
	
	public void deleteAll();
}
