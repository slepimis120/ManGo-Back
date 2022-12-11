package mango.controller;

import java.util.Collection;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mango.dto.PassengerDTO;
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
@RequestMapping("/api/v1/passenger")
public class PassengerController {
	@Autowired
	PassengerService service;
	
	@PostMapping
	public ResponseEntity insert(@RequestBody PassengerDTO passengerDTO) {
        PassengerDTOMapper mapper = new PassengerDTOMapper(new ModelMapper());
        Passenger newPassenger = mapper.fromDTOtoPassenger(passengerDTO);
        Passenger response = (Passenger) service.insert(newPassenger);
        return new ResponseEntity(response, HttpStatus.NO_CONTENT);
		
	}
	
	@GetMapping
	public ResponseEntity getUsers(@RequestBody Integer page, Integer size) {
		UserResponseDTO response = service.getArray(page, size, PassengerService.allPassengers);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@GetMapping("/{email}")
	public User findUser(@PathVariable String email) {
		return service.find(email);
	}

	@PostMapping
	public User insert(@RequestBody User user) {
		return service.insert(user);
	}
	
	@PutMapping
	public User update(@RequestBody User user) {
		return service.update(user);
	}
	
	@DeleteMapping("/{email}")
	public User delete(@PathVariable String email) {
		return service.delete(email);
	}
	
	@DeleteMapping
	public void deleteAll() {
		service.deleteAll();
	}
}