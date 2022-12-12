package mango.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import mango.dto.ExpandedUserDTO;
import mango.dto.NoteDTO;
import mango.dto.NoteResponseDTO;
import mango.dto.UserDTO;
import mango.dto.UserMessageDTO;
import mango.dto.UserMessageResponseDTO;
import mango.dto.UserResponseDTO;
import mango.model.Note;
import mango.model.Passenger;
import mango.model.User;
import mango.model.UserMessage;
import mango.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

	public static Map<Integer, User> allUsers = new HashMap<Integer, User>();
	public static Map<Integer, Note> allNotes = new HashMap<Integer, Note>();
	public static Map<Integer, UserMessage> allMessages = new HashMap<Integer, UserMessage>();

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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ssZ");
		String dateFormated = date.format(formatter);
		Note note = new Note(size + 1, message, date, id);
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
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ssZ");
					String dateFormated = currentNote.getDate().format(formatter);
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
			if(entry.getValue().getReceiverId() == id || entry.getValue().getSenderId() == id){
				UserMessage currentMessage = entry.getValue();
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ssZ");
				String dateFormated = currentMessage.getTimeOfSending().format(formatter);
				
				UserMessageDTO currentMessageDTO = new UserMessageDTO(currentMessage.getId(), dateFormated,
						currentMessage.getSenderId(), currentMessage.getReceiverId(), currentMessage.getMessage(),
						currentMessage.getType(), currentMessage.getRideId());
				returnList.add(currentMessageDTO);
			}
		}
		return new UserMessageResponseDTO(returnList.size(), returnList);
	}

	public UserMessageDTO sendMessage(Integer id, Integer receiverId, String message, String type,
			Integer rideId) {
		int size = allMessages.size();
		LocalDateTime timeOfSending = LocalDateTime.now();
		UserMessage userMessage = new UserMessage(size + 1, timeOfSending, id, receiverId, message, type, rideId);
		allMessages.put(size + 1, userMessage);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ssZ");
		String dateFormated = userMessage.getTimeOfSending().format(formatter);
		
		UserMessageDTO userMessageDTO = new UserMessageDTO(userMessage.getId(), dateFormated,
						userMessage.getSenderId(), userMessage.getReceiverId(), userMessage.getMessage(),
						userMessage.getType(), userMessage.getRideId());
		return userMessageDTO;
	}
	

}
