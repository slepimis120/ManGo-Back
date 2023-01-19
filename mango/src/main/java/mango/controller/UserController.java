package mango.controller;

import mango.dto.*;
import mango.model.User;
import mango.security.jwt.JwtTokenUtil;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import mango.service.UserService;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"}, allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService service;

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil tokenUtils;
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
	
	@GetMapping("/{id}/ride")
	public ResponseEntity getRides(@PathVariable Integer id, @RequestParam Integer page, Integer size, String sort, String from, String to){
		RideCountDTO response = service.userRides(id, page, size, sort, from, to);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping(value="/login")
	public ResponseEntity logIn(@RequestBody UserLoginDTO request) {
		UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(request.getEmail(),
				request.getPassword());
		Authentication auth = authenticationManager.authenticate(authReq);

		SecurityContext sc = SecurityContextHolder.getContext();
		sc.setAuthentication(auth);

		User user = service.getByEmail(request.getEmail());
		String token = tokenUtils.generateToken(request.getEmail(),sc.getAuthentication().getAuthorities().toArray()[0].toString(),user.getId()); // prosledjujemo email, role i id korisnika
		String refreshToken = tokenUtils.generateRefreshToken(request.getEmail(),sc.getAuthentication().getAuthorities().toArray()[0].toString(),user.getId());

		UserLoginResponseDTO jwt = new UserLoginResponseDTO();
		jwt.setAccessToken(token);
		jwt.setRefreshToken(refreshToken);

		return ResponseEntity.status(HttpStatus.OK).body(jwt);
	}
}
