package mango.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import mango.dto.ExpandedUserDTO;
import mango.dto.UserDTO;
import mango.dto.UserResponseDTO;
import mango.model.User;
import mango.service.interfaces.IUserService;

public class UserService implements IUserService{

	public static Map<Integer, User> allUsers = new HashMap<Integer, User>();

	@Override
	public UserResponseDTO getArray(Integer page, Integer size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO insert(ExpandedUserDTO data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO update(Integer id, ExpandedUserDTO update) {
		// TODO Auto-generated method stub
		return null;
	}
	

}