package mango.service;

import mango.dto.*;
import mango.model.Activation;
import mango.model.Driver;
import mango.model.Passenger;
import mango.model.Ride;
import mango.repository.*;
import mango.security.WebSecurityConfiguration;
import mango.service.email.EmailSenderService;
import mango.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Primary
public class PassengerService implements IUserService{

	@Autowired
	private RideRepository rideRepository;

	@Autowired
	private ActivationRepository activationRepository;


	@Autowired
	WebSecurityConfiguration webSecurityConfiguration;

	@Autowired
	private PassengerRepository passengerRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EmailSenderService emailSenderService;

	@Autowired
	private DriverRepository driverRepository;


	public Passenger insertPassenger(ExpandedUserDTO data) {
		if(emailExists(data.getEmail())){
			return null;
		}
		Passenger passenger = new Passenger(data);
		passenger = passengerRepository.save(passenger);
		Activation activation = new Activation(passenger, new Date(), false);
		activation = activationRepository.save(activation);
		emailSenderService.sendSimpleEmail(passenger.getEmail(), activation.getId());
		return passenger;
	}
	
	@Override
	public UserResponseDTO getArray(Integer page, Integer size) {
		int offset = page * size;
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
		Driver driver = driverRepository.findById(id).orElse(null);
		if (driver != null) {
			UserDTO userDTO = new UserDTO(driver);
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
			if(update.getProfilePicture() != null) 
				passenger.setProfilePicture(update.getProfilePicture());
			if(update.getTelephoneNumber() != null)
				passenger.setTelephoneNumber(update.getTelephoneNumber());
			passenger = passengerRepository.save(passenger);
			return new UserDTO(passenger);
		}
		return null;
	}

	public UserDTO changePassword(Integer id, ChangePasswordDTO changePasswordDTO){
		Passenger passenger = passengerRepository.findById(id).orElse(null);
		if(passenger != null){
			passenger.setPassword(changePasswordDTO.getNewPassword());
			passengerRepository.save(passenger);
			return new UserDTO(passenger);
		}
		return null;

	}

	public RideCountDTO passengerRides(Integer id, Integer page, Integer size, String sort, String from, String to){
		if(passengerRepository.findById(id).orElse(null) == null){
			return null;
		}
		int start = (page - 1) * size;
		int end = page * size;
		RideCountDTO returnList = new RideCountDTO();
		ArrayList<Ride> rideList = new ArrayList<Ride>();
		System.out.println(from + to);
		Date fromTime= null;
		Date toTime = null;
		try {
			fromTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(from);
			toTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(to);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		int i = 0;
		for (Ride entry : rideRepository.findAll()) {
			for (Passenger passenger: entry.getPassengers()){
				if(passenger.getId().equals(id)){
					if(entry.getEndTime() == null) continue;
					if( (entry.getStartTime().after(fromTime) || entry.getStartTime().equals(fromTime))
							&& (entry.getEndTime().before(toTime) || entry.getEndTime().equals(toTime)) ){
						if(i >= start && i < end){
							rideList.add(entry);
							i++;
						}

					}
				}
			}
		}
		returnList.setResults(rideList);
		returnList.setTotalCount(rideList.size());
		return returnList;
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

	public boolean emailExists(String email){
		List<Passenger> passengers = passengerRepository.findAll();
		for(Passenger passenger : passengers){
			if(email.equals(passenger.getEmail())){
				return true;
			}
		}
		return false;
	}

	public boolean activatePassenger(Activation activation){
		activation.setActivated(true);
		activationRepository.save(activation);
		return true;
	}


	public Activation getActivation(Integer id){
		return activationRepository.getById(id);
	}

	public Passenger getByEmail(String email){
		return passengerRepository.findByEmail(email);
	}

	public Passenger getById(Integer id){
		return passengerRepository.findById(id).orElse(null);
	}
}
