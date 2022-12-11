package mango.controller;

import java.util.Collection;

import mango.dto.ExpandedUserDTO;
import mango.dto.PageDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mango.dto.PassengerDTO;
import mango.dto.UserDTO;
import mango.mapper.LocationDTOMapper;
import mango.mapper.PassengerDTOMapper;
import mango.model.Location;
import mango.model.Passenger;
import mango.model.User;
import mango.service.PassengerService;
import mango.service.interfaces.IUserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import mango.dto.UserResponseDTO;

@RestController
@RequestMapping("/api/passenger")
public class PassengerController {
	@Autowired
	PassengerService service;
	
	@PostMapping
	public ResponseEntity insert(@RequestBody ExpandedUserDTO data) {
        UserDTO response =  service.insert(data);
        return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity getUsers(@RequestParam  PageDTO page) {
		UserResponseDTO response = service.getArray(page.getPage(), page.getSize());
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity findUser(@PathVariable Integer id) {
		UserDTO response =  service.find(id);
		return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity update(@PathVariable Integer id, @RequestBody ExpandedUserDTO update) {
		UserDTO response = service.update(id, update);
		return new ResponseEntity(response, HttpStatus.OK);
	}
	
}