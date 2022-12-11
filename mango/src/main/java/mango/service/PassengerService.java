package mango.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mango.dto.ExpandedUserDTO;
import mango.dto.UserDTO;
import mango.dto.UserResponseDTO;
import mango.model.Passenger;
import mango.service.interfaces.IUserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class PassengerService implements IUserService{
	
	public static Map<Integer, Passenger> allPassengers = new HashMap<Integer, Passenger>();

	@Override
	public UserDTO insert(ExpandedUserDTO data) {
		Passenger passenger = new Passenger(data);
		int size = UserService.allUsers.size();
		passenger.setId(size + 1);
		allPassengers.put(passenger.getId(), passenger);
		UserService.allUsers.put(passenger.getId(), passenger);
		return new UserDTO(passenger);
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
	public UserDTO find(Integer id) {
		Passenger passenger = allPassengers.get(id);
		if (passenger != null) {
			UserDTO userDTO = new UserDTO(passenger);
			return userDTO;
		}
		return null;
	}



	@Override
	public UserDTO update(Integer id, ExpandedUserDTO update) {
		Passenger passenger = allPassengers.get(id);
		if (passenger != null) {
			passenger.setName(update.getName());
			passenger.setSurname(update.getSurname());
			passenger.setAddress(update.getAddress());
			passenger.setEmail(update.getEmail());
			passenger.setPassword(update.getPassword());
			if(update.getProfilePicture() != null) 
				passenger.setProfilePicture(update.getProfilePicture());
			if(update.getTelephoneNumber() != null)
				passenger.setTelephoneNumber(update.getTelephoneNumber());
			return new UserDTO(passenger);
			
		}
		throw new RuntimeException();
	}

}
