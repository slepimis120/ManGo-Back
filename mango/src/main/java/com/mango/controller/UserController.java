package com.mango.controller;

import com.mango.dto.*;
import com.mango.model.User;
import com.mango.security.jwt.JwtTokenUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import com.mango.dto.*;
import org.hibernate.validator.constraints.Length;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.mango.service.UserService;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService service;

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil tokenUtils;

	@PreAuthorize("hasAuthority(\"ROLE_PASSENGER\")")
	@RequestMapping(value="/{id}/changePassword",method = RequestMethod.PUT)
	public ResponseEntity changePassword(@PathVariable Integer id, @RequestBody @Valid ChangePasswordDTO password) {
		HttpStatus response = service.changePassword(id, password.getNewPassword(), password.getVerification());
		if (response == HttpStatus.NOT_FOUND)
			return ResponseEntity.status(response).body("User does not exist!");
		else if(response == HttpStatus.BAD_REQUEST)
			return ResponseEntity.status(response).body("Current password is not matching!");
		return ResponseEntity.status(response).body("Password successfully changed!");
	}

	@PreAuthorize("permitAll()")
	@GetMapping("/{id}/resetPassword")
	public ResponseEntity sendResetMail(@PathVariable Integer id) {
		HttpStatus response = service.sendResetMail(id);
		if (response == HttpStatus.NOT_FOUND)
			return ResponseEntity.status(response).body("User does not exist!");
		return ResponseEntity.status(response).body("Email with reset code has been sent!");
	}

	@PreAuthorize("permitAll()")
	@RequestMapping(value="/{id}/resetPassword",method = RequestMethod.PUT)
	public ResponseEntity resetPassword(@PathVariable Integer id, @RequestBody @Valid ResetPasswordDTO password) {
		HttpStatus response = service.resetPassword(id, password.getNewPassword(), password.getCode());
		if (response == HttpStatus.NOT_FOUND)
			return ResponseEntity.status(response).body("User does not exist!");
		return ResponseEntity.status(response).body("Password successfully changed!");
	}

	@PreAuthorize("hasAuthority(\"ROLE_ADMIN\")")
	@GetMapping
	public ResponseEntity getUsers(@RequestParam Integer page, Integer size) {
		UserResponseDTO response = service.getArray(page,size);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PreAuthorize("hasAuthority(\"ROLE_ADMIN\")")
	@RequestMapping(value="/{id}/block",method = RequestMethod.PUT)
	public ResponseEntity block(@PathVariable Integer id) {
		HttpStatus status = service.block(id);
		if(status == HttpStatus.NOT_FOUND) return ResponseEntity.status(status).body("User does not exist!");
		if(status == HttpStatus.BAD_REQUEST) return ResponseEntity.status(status).body("User already blocked!");
		return ResponseEntity.status(status).body("User is successfully blocked");
	}

	@PreAuthorize("hasAuthority(\"ROLE_ADMIN\")")
	@RequestMapping(value="/{id}/unblock",method = RequestMethod.PUT)
	public ResponseEntity unblock(@PathVariable Integer id) {
		HttpStatus status = service.unblock(id);
		if(status == HttpStatus.NOT_FOUND) return ResponseEntity.status(status).body("User does not exist!");
		if(status == HttpStatus.BAD_REQUEST) return ResponseEntity.status(status).body("User is not blocked!");
		return ResponseEntity.status(status).body("User is successfully unblocked");
	}

	@PreAuthorize("hasAuthority(\"ROLE_ADMIN\")")
	@PostMapping("/{id}/note")
	public ResponseEntity insertNote(@PathVariable Integer id, @RequestBody @Valid @NotNull @Length(max = 500)JSONObject message) {
        NoteDTO response =  service.insertNote(id, message.get("message").toString());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PreAuthorize("hasAuthority(\"ROLE_ADMIN\")")
	@GetMapping("/{id}/note")
	public ResponseEntity getNote(@PathVariable Integer id, @RequestParam Integer page, Integer size) {
		NoteResponseDTO response = service.getNotes(id, page, size);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PreAuthorize("hasAuthority(\"ROLE_ADMIN\")")
	@GetMapping("/{id}/message")
	public ResponseEntity getUserMessages(@PathVariable Integer id) {
		UserMessageResponseDTO response = service.getUserMessages(id);
		if(response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PreAuthorize("hasAuthority(\"ROLE_PASSENGER\")")
	@PostMapping("/{id}/message")
	public ResponseEntity sendMessage(@PathVariable Integer id, @RequestBody @Valid MessageDTO messageDTO) {
        UserMessageDTO response =  service.sendMessage(1, id, messageDTO.getMessage(), messageDTO.getType(), messageDTO.getRideId());
		if(response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist!//Receiver does not exist!//Sender does not exist");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PreAuthorize("hasAuthority(\"ROLE_ADMIN\")")
	@GetMapping("/{id}/ride")
	public ResponseEntity getRides(@PathVariable Integer id, @RequestParam Integer page, Integer size, String sort, String from, String to){
		RideCountDTO response = service.userRides(id, page, size, sort, from, to);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PreAuthorize("permitAll()")
	@PostMapping(value="/login")
	public ResponseEntity logIn(@RequestBody UserLoginDTO request) {
		UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(request.getEmail(),
				request.getPassword());
		try{
			Authentication auth = authenticationManager.authenticate(authReq);
			SecurityContext sc = SecurityContextHolder.getContext();
			sc.setAuthentication(auth);

		}catch (Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong username or password!");
		}
		User user = service.getByEmail(request.getEmail());
		if(user.getDecriminatorValue().toString().equals("PASSENGER")){
			if(!service.checkActivation(user.getId())){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Passenger profile isn't activated yet! Check your email");
			}
		}
		if(user.isBlocked()){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is blocked");
		}
		String token = tokenUtils.generateToken(request.getEmail(),user.getDecriminatorValue(),user.getId()); // prosledjujemo email, role i id korisnika
		String refreshToken = tokenUtils.generateRefreshToken(request.getEmail(),user.getDecriminatorValue(),user.getId());

		UserLoginResponseDTO jwt = new UserLoginResponseDTO();
		jwt.setAccessToken(token);
		jwt.setRefreshToken(refreshToken);

		return ResponseEntity.status(HttpStatus.OK).body(jwt);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}
}
