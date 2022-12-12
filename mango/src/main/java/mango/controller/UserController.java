package mango.controller;

import mango.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mango.service.UserService;
import mango.dto.ExpandedUserDTO;
import mango.dto.LoginDTO;
import mango.dto.NoteDTO;
import mango.dto.NoteResponseDTO;
import mango.dto.UserDTO;
import mango.dto.UserMessageDTO;
import mango.dto.UserMessageResponseDTO;
import mango.dto.UserResponseDTO;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	UserService service;
	
	@GetMapping
	public ResponseEntity getUsers(@RequestParam Integer page, Integer size) {
		UserResponseDTO response = service.getArray(page, size);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@RequestMapping(value="/{id}/block",method = RequestMethod.PUT)
	public ResponseEntity block(@PathVariable Integer id) {
		Boolean response = service.block(id);
		return new ResponseEntity(response, HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/{id}/unblock",method = RequestMethod.PUT)
	public ResponseEntity unblock(@PathVariable Integer id) {
		Boolean response = service.unblock(id);
		return new ResponseEntity(response, HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/{id}/note")
	public ResponseEntity insertNote(@PathVariable Integer id, @RequestBody String message) {
        NoteDTO response =  service.insertNote(id, message);
        return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}/note")
	public ResponseEntity getNote(@PathVariable Integer id, @RequestParam Integer page, Integer size) {
		NoteResponseDTO response = service.getNotes(id, page, size);
		return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}/messages")
	public ResponseEntity getUserMessages(@PathVariable Integer id) {
		UserMessageResponseDTO response = service.getUserMessages(id);
		return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@PostMapping("/{id}/messages")
	public ResponseEntity sendMessage(@PathVariable Integer id, @RequestBody Integer receiverId, String message,
			String type, Integer rideId) {
        UserMessageDTO response =  service.sendMessage(id, receiverId, message, type, rideId);
        return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody String email, String password) {
        LoginDTO response =  service.login(email, password);
        return new ResponseEntity(response, HttpStatus.OK);
	}
	

}
