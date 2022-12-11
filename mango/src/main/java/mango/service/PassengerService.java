package mango.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import mango.dto.PassengerDTO;
import mango.dto.UserDTO;
import mango.dto.UserResponseDTO;
import mango.model.Passenger;
import mango.model.User;
import mango.service.interfaces.IUserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class PassengerService implements IUserService{
	
	//TODO: create repository classes after connecting to the database
	public static Map<Integer, User> allPassengers = new HashMap<Integer, User>();

	@Override
	public User insert(User user) {
		int size = allPassengers.size();
		user.setId(size + 1);
		allPassengers.put(user.getId(), user);
		UserService.allUsers.put(user.getId(), user);
		return user;
	}
	
	@Override
	public UserResponseDTO getArray(Integer page, Integer size, Map<Integer, User> data) {
		int start = page * size;
		Object[] allUsersArray = data.entrySet().toArray();
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
	public User find(String email) {
		User found = allPassengers.get(email);
		if (found != null)
			return allPassengers.get(email);
		throw new RuntimeException();
	}



	@Override
	public User update(User user) {
		User found = allPassengers.get(user.getEmail());
		if (found != null) {
			found.setName(user.getName());
			found.setSurname(user.getSurname());
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
