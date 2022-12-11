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
	public static Map<Integer, Passenger> allPassengers = new HashMap<Integer, Passenger>();

	@Override
	public User insert(User user) {
		int size = allPassengers.size();
		user.setId(size + 1);
		Passenger passenger = new Passenger(user);
		allPassengers.put(user.getId(), passenger);
		UserService.allUsers.put(user.getId(), user);
		return user;
	}
	
	@Override
	public UserResponseDTO getArray(Integer page, Integer size) {
		int start = (page - 1) * size;
		int end = page * size;
		ArrayList<UserDTO> returnList = new ArrayList<UserDTO>();
		int i = 0;
		for (Map.Entry<Integer, Passenger> entry : allPassengers.entrySet()) {
			if(i >= start && i < end){
				Passenger currentPassenger = entry.getValue();
				UserDTO currentUserDTO = new UserDTO(currentPassenger.getId(), currentPassenger.getName(), currentPassenger.getSurname(),
						currentPassenger.getProfilePicture(), currentPassenger.getTelephoneNumber(), currentPassenger.getEmail(), currentPassenger.getAddress());
				returnList.add(currentUserDTO);
			}
			i++;
		}
		return new UserResponseDTO(allPassengers.size(), returnList);
	}
	
	

	@Override
	public User find(String email) {
		User found = allPassengers.get(email);
		if (found != null)
			return allPassengers.get(email);
		return null;
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
