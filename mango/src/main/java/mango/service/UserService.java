package mango.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import mango.dto.ExpandedUserDTO;
import mango.dto.NoteDTO;
import mango.dto.UserDTO;
import mango.dto.UserResponseDTO;
import mango.model.Note;
import mango.model.Passenger;
import mango.model.User;
import mango.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

	public static Map<Integer, User> allUsers = new HashMap<Integer, User>();
	public static Map<Integer, Note> allNotes = new HashMap<Integer, Note>();

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
		return new UserResponseDTO(allUsers.size(), returnList);
	
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

	

}
