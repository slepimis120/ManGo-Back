package mango.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import mango.dto.UserDTO;
import mango.dto.UserResponseDTO;
import mango.model.User;
import mango.service.interfaces.IUserService;

public class UserService implements IUserService{

	public static Map<Integer, User> allUsers = new HashMap<Integer, User>();
	
	@Override
	public UserResponseDTO getArray(Integer page, Integer size) {
		int start = page * size;
		Object[] allUsersArray = allUsers.entrySet().toArray();
		ArrayList<UserDTO> returnList = new ArrayList<UserDTO>();
		for(int i=0; i < size; i++) {
			User currentUser = (User) allUsersArray[start + i];
			UserDTO currentUserDTO = new UserDTO(currentUser.getId(), currentUser.getName(), currentUser.getSurname(),
					currentUser.getProfilePicture(), currentUser.getTelephoneNumber(), currentUser.getEmail(), currentUser.getAddress());
			returnList.add(currentUserDTO);
		}
		UserResponseDTO response = new UserResponseDTO(allUsersArray.length, returnList);
		return response;	
	}

	@Override
	public User insert(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User delete(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User find(String email) {
		// TODO Auto-generated method stub
		return null;
	}


}
