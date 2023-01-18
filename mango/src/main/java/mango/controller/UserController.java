package mango.controller;

import mango.dto.*;
import mango.service.interfaces.IUserService;
import org.apache.coyote.Response;
import org.json.simple.JSONObject;
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
	public ResponseEntity changePassword(@PathVariable Integer id, @RequestBody ChangePasswordDTO password) {
		HttpStatus response = service.changePassword(id, password.getNewPassword(), password.getVerification());
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
	public ResponseEntity resetPassword(@PathVariable Integer id, @RequestBody ResetPasswordDTO password) {
		HttpStatus response = service.resetPassword(id, password.getNewPassword(), password.getCode());
		if (response == HttpStatus.NOT_FOUND)
			return ResponseEntity.status(response).body("User does not exist!");
		return ResponseEntity.status(response).body("Password successfully changed!");
	}
	
	@GetMapping
	public ResponseEntity getUsers(@RequestParam Integer page, Integer size) {
		UserResponseDTO response = service.getArray(page,size);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@RequestMapping(value="/{id}/block",method = RequestMethod.PUT)
	public ResponseEntity block(@PathVariable Integer id) {
		HttpStatus status = service.block(id);
		if(status == HttpStatus.NOT_FOUND) return ResponseEntity.status(status).body("User does not exist!");
		if(status == HttpStatus.BAD_REQUEST) return ResponseEntity.status(status).body("User already blocked!");
		return ResponseEntity.status(status).body("User is successfully blocked");
	}
	
	@RequestMapping(value="/{id}/unblock",method = RequestMethod.PUT)
	public ResponseEntity unblock(@PathVariable Integer id) {
		HttpStatus status = service.unblock(id);
		if(status == HttpStatus.NOT_FOUND) return ResponseEntity.status(status).body("User does not exist!");
		if(status == HttpStatus.BAD_REQUEST) return ResponseEntity.status(status).body("User is not blocked!");
		return ResponseEntity.status(status).body("User is successfully unblocked");
	}
	
	@PostMapping("/{id}/note")
	public ResponseEntity insertNote(@PathVariable Integer id, @RequestBody JSONObject message) {
        NoteDTO response =  service.insertNote(id, message.get("message").toString());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/{id}/note")
	public ResponseEntity getNote(@PathVariable Integer id, @RequestParam Integer page, Integer size) {
		NoteResponseDTO response = service.getNotes(id, page, size);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/{id}/message")
	public ResponseEntity getUserMessages(@PathVariable Integer id) {
		UserMessageResponseDTO response = service.getUserMessages(id);
		if(response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping("/{id}/message")
	public ResponseEntity sendMessage(@PathVariable Integer id, @RequestBody MessageDTO messageDTO) {
        UserMessageDTO response =  service.sendMessage(id, messageDTO.getReceiverId(), messageDTO.getMessage(), messageDTO.getType(), messageDTO.getRideId());
		if(response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist!//Receiver does not exist!//Sender does not exist");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody String email, String password) {
        LoginDTO response =  service.login(email, password);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/{id}/ride")
	public ResponseEntity getRides(@PathVariable Integer id, @RequestParam Integer page, Integer size, String sort, String from, String to){
		RideCountDTO response = service.userRides(id, page, size, sort, from, to);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
