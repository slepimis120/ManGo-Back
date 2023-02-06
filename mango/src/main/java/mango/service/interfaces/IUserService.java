package mango.service.interfaces;

import java.util.Collection;
import java.util.Map;

import mango.dto.ExpandedUserDTO;
import mango.dto.UserDTO;
import mango.dto.UserResponseDTO;
import mango.model.User;

public interface IUserService {

	public UserResponseDTO getArray(Integer page, Integer size);
	
	public UserDTO find(Integer id);

	public UserDTO update(Integer id, ExpandedUserDTO update);



}
