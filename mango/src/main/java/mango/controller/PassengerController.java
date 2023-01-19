package mango.controller;

import mango.dto.ExpandedUserDTO;
import mango.dto.RideCountDTO;
import mango.dto.UserDTO;
import mango.dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mango.service.PassengerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/passenger")
public class PassengerController {
	
	@Autowired
	PassengerService service;


	
	@PostMapping
	public ResponseEntity insert(@RequestBody ExpandedUserDTO data) {
        UserDTO response =  service.insert(data);
		if(response == null){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with that email already exists!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping
	public ResponseEntity getUsers(@RequestParam Integer page, Integer size) {
		UserResponseDTO response = service.getArray(page, size);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity findUser(@PathVariable Integer id) {
		UserDTO response =  service.find(id);
		if(response == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Passenger does not exist!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public ResponseEntity update(@PathVariable Integer id, @RequestBody ExpandedUserDTO update) {
		UserDTO response = service.update(id, update);
		if(response == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Passenger does not exist!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/{id}/ride")
	public ResponseEntity getRides(@PathVariable Integer id, @RequestParam Integer page, Integer size, String sort, String from, String to){
		RideCountDTO response = service.passengerRides(id, page, size, sort, from, to);
		if(response == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Passenger does not exist!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}