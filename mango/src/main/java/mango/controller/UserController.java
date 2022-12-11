package mango.controller;

import mango.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mango.service.UserService;
import mango.dto.UserResponseDTO;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	IUserService service;
	
	@GetMapping
	public ResponseEntity getUsers(@RequestBody Integer page, Integer size) {
		UserResponseDTO response = service.getArray(page, size);
		return new ResponseEntity(response, HttpStatus.OK);
	}

}
