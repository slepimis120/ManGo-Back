package mango.service.interfaces;

import java.util.Collection;
import java.util.Map;

import mango.dto.UserResponseDTO;
import mango.model.User;

public interface IUserService {

	public UserResponseDTO getArray(Integer page, Integer size);
	
	public User find(String email);
	
	public User insert(User user);

	public User update(User user);
	
	public User delete(String email);
	
	public void deleteAll();
}
