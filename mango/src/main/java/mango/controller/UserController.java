package mango.controller;

import mango.dto.*;
import mango.service.interfaces.IUserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mango.service.UserService;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	UserService service;

	@RequestMapping(value="/{id}/changePassword",method = RequestMethod.PUT)
	public ResponseEntity changePassword(@PathVariable Integer id, @RequestBody String newPassword, String oldPassword) {
		HttpStatus response = service.changePassword(id, newPassword, oldPassword);
		if (response == HttpStatus.NOT_FOUND)
			return ResponseEntity.status(response).body("User does not exist!");
		else if(response == HttpStatus.BAD_REQUEST)
			return ResponseEntity.status(response).body("Current password is not matching!");
		return ResponseEntity.status(response).body("Password successfully changed!");
	}
	@GetMapping("/{id}/resetPassword")
	public ResponseEntity sendResetMail(@PathVariable Integer id) {
		HttpStatus response = service.sendResetMail(id);
		if (response == HttpStatus.NOT_FOUND)
			return ResponseEntity.status(response).body("User does not exist!");
		return ResponseEntity.status(response).body("Email with reset code has been sent!");
	}
	@RequestMapping(value="/{id}/resetPassword",method = RequestMethod.PUT)
	public ResponseEntity resetPassword(@PathVariable Integer id, @RequestBody String newPassword, String code) {
		HttpStatus response = service.resetPassword(id, newPassword, code);
		if (response == HttpStatus.NOT_FOUND)
			return ResponseEntity.status(response).body("User does not exist!");
		return ResponseEntity.status(response).body("Password successfully changed!");
	}
	
	@GetMapping
	public ResponseEntity getUsers(@RequestBody PagingDTO pagingDTO) {
		UserResponseDTO response = service.getArray(pagingDTO.getPage(), pagingDTO.getSize());
		return ResponseEntity.status(HttpStatus.OK).body(response);
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
	public ResponseEntity getNote(@PathVariable Integer id, @RequestBody PagingDTO pagingDTO) {
		NoteResponseDTO response = service.getNotes(id, pagingDTO.getPage(), pagingDTO.getSize());
		return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}/message")
	public ResponseEntity getUserMessages(@PathVariable Integer id) {
		UserMessageResponseDTO response = service.getUserMessages(id);
		return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@PostMapping("/{id}/message")
	public ResponseEntity sendMessage(@PathVariable Integer id, @RequestBody MessageDTO messageDTO) {
        UserMessageDTO response =  service.sendMessage(id, messageDTO.getReceiverId(), messageDTO.getMessage(), messageDTO.getType(), messageDTO.getRideId());
        return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody String email, String password) {
        LoginDTO response =  service.login(email, password);
        return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}/ride")
	public ResponseEntity getRides(@PathVariable Integer id, @RequestBody PagingDTO pagingDTO){
		RideCountDTO response = service.userRides(id, pagingDTO.getPage(), pagingDTO.getSize(), pagingDTO.getSort(), pagingDTO.getFrom(), pagingDTO.getTo());
		return new ResponseEntity(response, HttpStatus.OK);
	}
}
