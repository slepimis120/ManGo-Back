package mango.controller;

import mango.dto.*;
import mango.model.User;
import mango.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping
	public ResponseEntity getUsers(@RequestBody PagingDTO pagingDTO) {
		UserResponseDTO response = service.getArray(pagingDTO.getPage(), pagingDTO.getSize());
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
	
	//@PostMapping("/login")
	//public ResponseEntity login(@RequestBody String email, String password) {
    //    LoginDTO response =  service.login(email, password);
    //    return new ResponseEntity(response, HttpStatus.OK);
	//}
	
	@GetMapping("/{id}/ride")
	public ResponseEntity getRides(@PathVariable Integer id, @RequestBody PagingDTO pagingDTO){
		RideCountDTO response = service.userRides(id, pagingDTO.getPage(), pagingDTO.getSize(), pagingDTO.getSort(), pagingDTO.getFrom(), pagingDTO.getTo());
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@PostMapping(value="/login")
	public ResponseEntity logIn(@RequestBody UserLoginDTO request) {
		UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(request.getEmail(),
				request.getPassword());
		Authentication auth = authenticationManager.authenticate(authReq);

		SecurityContext sc = SecurityContextHolder.getContext();
		System.out.println("DDD");
		sc.setAuthentication(auth);

		System.out.println("EEE");
		User user = service.getByEmail(request.getEmail());

		System.out.println("FFF");
		String token = tokenUtils.generateToken(request.getEmail(),sc.getAuthentication().getAuthorities().toArray()[0].toString(),user.getId()); // prosledjujemo email, role i id korisnika
		String refreshToken = tokenUtils.generateRefreshToken(request.getEmail(),sc.getAuthentication().getAuthorities().toArray()[0].toString(),user.getId());

		System.out.println("GGG");
		UserLoginResponseDTO jwt = new UserLoginResponseDTO();
		jwt.setAccessToken(token);
		jwt.setRefreshToken(refreshToken);

		System.out.println("HHH");
		return ResponseEntity.status(HttpStatus.OK).body(jwt);
	}
}
