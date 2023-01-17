package mango.service;

import mango.dto.ExpandedUserDTO;
import mango.dto.RideCountDTO;
import mango.dto.UserDTO;
import mango.dto.UserResponseDTO;
import mango.model.Passenger;
import mango.model.Ride;
import mango.model.User;
import mango.repository.PassengerRepository;
import mango.repository.RideRepository;
import mango.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Primary
public class PassengerService implements IUserService{

	@Autowired
	private RideRepository rideRepository;

	@Autowired
	private PassengerRepository passengerRepository;

	@Override
	public UserDTO insert(ExpandedUserDTO data) {
		Passenger passenger = new Passenger(data);
		passengerRepository.save(passenger);
		UserService.allUsers.put(passenger.getId(), passenger);
		return new UserDTO(passenger);
	}
	
	@Override
	public UserResponseDTO getArray(Integer page, Integer size) {
		int offset = (page - 1) * size;
		ArrayList<UserDTO> returnList = new ArrayList<UserDTO>();
		List<Passenger> users = passengerRepository.fetchPassengerOffset(offset, size);
		for(int i = 0; i < users.size(); i++){
			UserDTO currentUser = new UserDTO(users.get(i));
			returnList.add(currentUser);
		}
		return new UserResponseDTO(returnList.size(), returnList);
	}

	@Override
	public UserDTO find(Integer id) {
		Passenger passenger = passengerRepository.findById(id).orElse(null);
		if (passenger != null) {
			UserDTO userDTO = new UserDTO(passenger);
			return userDTO;
		}
		return null;
	}

	@Override
	public UserDTO update(Integer id, ExpandedUserDTO update) {
		Passenger passenger = passengerRepository.findById(id).orElse(null);
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

	public RideCountDTO passengerRides(Integer id, Integer page, Integer size, String sort, String from, String to){
		RideCountDTO count = new RideCountDTO();
		ArrayList<Ride> rideList = new ArrayList<Ride>();
		Integer rideCount = 0;
		for (Ride entry : rideRepository.findAll()) {
			for (Passenger passenger: entry.getPassengers()){
				if(passenger.getId().equals(id)){
					rideList.add(entry);
					rideCount = rideCount + 1;
				}
			}
		}
		count.setResults(rideList);
		count.setTotalCount(rideCount);
		return count;
	}

	public boolean isPassengerInRide(Integer rideId, Integer passengerId){
		List<Passenger> passengers = passengerRepository.getPassengerByRideId(rideId);
		for(Passenger passenger : passengers){
			if(passenger.getId().equals(passengerId)){
				return true;
			}
		}
		return false;
	}
}
