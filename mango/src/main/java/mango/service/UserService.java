package mango.service;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import mango.dto.*;
import mango.model.*;
import mango.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

	public static Map<Integer, User> allUsers = new HashMap<Integer, User>();
	public static Map<Integer, Note> allNotes = new HashMap<Integer, Note>();
	public static Map<Integer, UserMessage> allMessages = new HashMap<Integer, UserMessage>();

	RideService rideService;

	@Autowired
	public UserService(RideService rideService){
		this.rideService = rideService;
	}

	@Override
	public UserResponseDTO getArray(Integer page, Integer size) {
		int start = (page - 1) * size;
		int end = page * size;
		ArrayList<UserDTO> returnList = new ArrayList<UserDTO>();
		int i = 0;
		for (Map.Entry<Integer, User> entry : allUsers.entrySet()) {
			if(i >= start && i < end){
				User currentUser = entry.getValue();
				UserDTO currentUserDTO = new UserDTO(currentUser.getId(), currentUser.getName(), currentUser.getSurname(),
						currentUser.getProfilePicture(), currentUser.getTelephoneNumber(), currentUser.getEmail(), currentUser.getAddress());
				returnList.add(currentUserDTO);
			}
			i++;
		}
		return new UserResponseDTO(returnList.size(), returnList);
	
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
	
	public Boolean block(Integer id) {
		User user = allUsers.get(id);
		user.setBlocked(true);
		return true;
	}
	public Boolean unblock(Integer id) {
		User user = allUsers.get(id);
		user.setBlocked(false);
		return true;
	}
	
	public NoteDTO insertNote(Integer id, String message) {
		int size = allNotes.size();
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
		String dateFormated = date.format(format);
		User user = new User();
		user.setId(id);
		Note note = new Note(size + 1, message, date, user);
		allNotes.put(note.getId(), note);
		NoteDTO noteDTO = new NoteDTO(note.getId(), message, dateFormated);
		return noteDTO;
	}

	public NoteResponseDTO getNotes(Integer id, Integer page, Integer size) {
		int start = (page - 1) * size;
		int end = page * size;
		ArrayList<NoteDTO> returnList = new ArrayList<NoteDTO>();
		int i = 0;
		for (Map.Entry<Integer, Note> entry : allNotes.entrySet()) {
			if(entry.getValue().getUserId() == id) {
				if(i >= start && i < end ){
					Note currentNote = entry.getValue();
					DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
					String dateFormated = currentNote.getDate().format(format);
					NoteDTO currentNoteDTO = new NoteDTO(currentNote.getId(), currentNote.getMessage(), dateFormated);
					returnList.add(currentNoteDTO);
				}
				i++;
			}	
		}
		return new NoteResponseDTO(returnList.size(), returnList);
	}

	public UserMessageResponseDTO getUserMessages(Integer id) {
		ArrayList<UserMessageDTO> returnList = new ArrayList<UserMessageDTO>();
		for (Map.Entry<Integer, UserMessage> entry : allMessages.entrySet()) {
			if(entry.getValue().getReceiver().getId().equals(id) || entry.getValue().getSender().getId().equals(id)){
				UserMessage currentMessage = entry.getValue();

				DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
				String dateFormated = currentMessage.getTimeOfSending().format(format);
				
				UserMessageDTO currentMessageDTO = new UserMessageDTO(currentMessage.getId(), dateFormated,
						currentMessage.getSender().getId(), currentMessage.getReceiver().getId(), currentMessage.getMessage(),
						currentMessage.getType().toString(), currentMessage.getRide().getId());
				returnList.add(currentMessageDTO);
			}
		}
		return new UserMessageResponseDTO(returnList.size(), returnList);
	}

	public UserMessageDTO sendMessage(Integer id, Integer receiverId, String message, String type,
			Integer rideId) {
		int size = allMessages.size();
		LocalDateTime timeOfSending = LocalDateTime.now();
		UserMessage userMessage = new UserMessage(size + 1, timeOfSending, id, receiverId, message, UserMessage.Type.valueOf(type), rideId);
		allMessages.put(size + 1, userMessage);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
		String dateFormated = userMessage.getTimeOfSending().format(format);

		return new UserMessageDTO(userMessage.getId(), dateFormated,
						userMessage.getSender().getId(), userMessage.getReceiver().getId(), userMessage.getMessage(),
						userMessage.getType().toString(), userMessage.getRide().getId());
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
		ArrayList<Ride> rideList = new ArrayList<Ride>();
		Integer rideCount = 0;
		Integer check = 0;
		for (Map.Entry<Integer, Ride> entry : rideService.getAllRides().entrySet()) {
			check = 0;
			for (Passenger passenger: entry.getValue().getPassengers()){
				if(passenger.getId().equals(id)){
					rideList.add(entry.getValue());
					rideCount = rideCount + 1;
					check = 1;
				}
			}
			if(check == 0){
				if(entry.getValue().getDriver().getId().equals(id)){
					rideList.add(entry.getValue());
					rideCount = rideCount + 1;
				}
			}
		}
		count.setResults(rideList);
		count.setTotalCount(rideCount);
		return count;
	}
}
