package mango.service;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import mango.dto.*;
import mango.model.*;
import mango.repository.*;
import mango.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

	@Autowired
	private RideRepository rideRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private NotesRepository notesRepository;
	@Autowired
	private MessagesRepository messagesRepository;

	@Autowired
	private ActivationRepository activationRepository;

	@Override
	public UserResponseDTO getArray(Integer page, Integer size) {
		int offset = (page - 1) * size;
		ArrayList<UserDTO> returnList = new ArrayList<UserDTO>();
		List<User> users = userRepository.getUsers(offset, size);
		for(int i = 0; i < users.size(); i++){
			UserDTO currentUser = new UserDTO(users.get(i));
			returnList.add(currentUser);
		}
		return  new UserResponseDTO(returnList.size(), returnList);
	}

	@Override
	
	public UserDTO find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO update(Integer id, ExpandedUserDTO update) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public HttpStatus block(Integer id) {
		User user = userRepository.findById(id).orElse(null);
		if(user == null) return HttpStatus.NOT_FOUND;
		if(user.isBlocked()) return HttpStatus.BAD_REQUEST;
		user.setBlocked(true);
		userRepository.save(user);
		return HttpStatus.NO_CONTENT;
	}
	public HttpStatus unblock(Integer id) {
		User user = userRepository.findById(id).orElse(null);
		if(user == null) return HttpStatus.NOT_FOUND;
		if(!user.isBlocked()) return HttpStatus.BAD_REQUEST;
		user.setBlocked(false);
		userRepository.save(user);
		return HttpStatus.NO_CONTENT;
	}
	
	public NoteDTO insertNote(Integer id, String message) {
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
		String dateFormated = date.format(format);
		User user = userRepository.findById(id).orElse(null);
		Note note = new Note( message, date, user);
		notesRepository.save(note);
		NoteDTO noteDTO = new NoteDTO(note.getId(), message, dateFormated);
		return noteDTO;
	}

	public NoteResponseDTO getNotes(Integer id, Integer page, Integer size) {
		int offset = (page - 1) * size;
		ArrayList<NoteDTO> returnList = new ArrayList<NoteDTO>();
		List<Note> notes = notesRepository.getNotes(id, offset, size);
		for(int i = 0; i < notes.size(); i++){
			Note currentNote = notes.get(i);
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
			String dateFormated = currentNote.getDate().format(format);
			NoteDTO currentNoteDTO = new NoteDTO(currentNote.getId(), currentNote.getMessage(), dateFormated);
			returnList.add(currentNoteDTO);
		}
		return new NoteResponseDTO(returnList.size(), returnList);
	}

	public UserMessageResponseDTO getUserMessages(Integer id) {
		if(userRepository.findById(id).orElse(null) == null) return null;
		ArrayList<UserMessageDTO> returnList = new ArrayList<UserMessageDTO>();
		List<UserMessage> messages = messagesRepository.getUserMessages(id, id);
		for(int i = 0; i < messages.size(); i++){
			UserMessageDTO currentMessage = new UserMessageDTO(messages.get(i));
			returnList.add(currentMessage);
		}
		return new UserMessageResponseDTO(returnList.size(), returnList);
	}

	public UserMessageDTO sendMessage(Integer senderId, Integer receiverId, String message, String type, Integer rideId) {
		if(userRepository.findById(senderId).orElse(null) == null) return null;
		if(userRepository.findById(receiverId).orElse(null) == null) return null;
		LocalDateTime timeOfSending = LocalDateTime.now();
		User sender = userRepository.findById(senderId).orElse(null);
		User receiver = userRepository.findById(receiverId).orElse(null);
		Ride ride = rideRepository.findById(rideId).orElse(null);
		UserMessage userMessage = new UserMessage(timeOfSending, sender, receiver, message, UserMessage.Type.valueOf(type), ride);
		messagesRepository.save(userMessage);
		return new UserMessageDTO(userMessage);
	}

	public LoginDTO login(String email, String password) {
		byte[] array = new byte[7];
	    new Random().nextBytes(array);
	    String accessToken = new String(array, Charset.forName("UTF-8"));
	    new Random().nextBytes(array);
	    String refreshToken = new String(array, Charset.forName("UTF-8"));
	    LoginDTO loginDTO = new LoginDTO(accessToken, refreshToken);
	    return loginDTO;
	}

	public RideCountDTO userRides(Integer id, Integer page, Integer size, String sort, String from, String to){
		RideCountDTO count = new RideCountDTO();
		List<Ride> rideList;
		if(findRidesByPassenger(id) == null){
			rideList = findRidesByDriver(id);
		}else{
			rideList = findRidesByPassenger(id);
		}
		count.setResults(rideList);
		count.setTotalCount(rideList.size());
		return count;
	}

	public List<Ride> findRidesByPassenger(Integer passengerId){
		return rideRepository.findRidesByPassenger(passengerId);
	}

	public List<Ride> findRidesByDriver(Integer driverId){
		return rideRepository.findRidesByDriver(driverId);
	}

	public User getByEmail(String email){
		return userRepository.findByEmail(email).orElse(null);
	}
	public HttpStatus changePassword(Integer id, String newPassword, String oldPassword) {
		User user = userRepository.findById(id).orElse(null);
		if(user != null){
			if(!user.getPassword().equals(oldPassword)){
				return HttpStatus.BAD_REQUEST;
			}
			user.setPassword(newPassword);
			userRepository.save(user);
			return HttpStatus.NO_CONTENT;
		}
		return  HttpStatus.NOT_FOUND;
	}
	public HttpStatus sendResetMail(Integer id){
		User user = userRepository.findById(id).orElse(null);
		if(user != null)
			return HttpStatus.NO_CONTENT;
		else return HttpStatus.NOT_FOUND;
	}

	public HttpStatus resetPassword(Integer id, String newPassword, String code) {
		User user = userRepository.findById(id).orElse(null);
		if(user != null) {
			user.setPassword(newPassword);
			userRepository.save(user);
			return HttpStatus.NO_CONTENT;
		}
		else return HttpStatus.NOT_FOUND;
	}

	public boolean checkActivation(Integer userId){
		return activationRepository.getActivation(userId).isActivated();
	}
}
