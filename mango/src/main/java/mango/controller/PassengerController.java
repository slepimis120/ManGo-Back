package mango.controller;

import mango.dto.ExpandedUserDTO;
import mango.dto.UserDTO;
import mango.dto.UserResponseDTO;
import mango.model.RideCount;
import mango.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	public ResponseEntity getUsers(@RequestParam Integer page, Integer size) {
		UserResponseDTO response = service.getArray(page, size);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity findUser(@PathVariable Integer id) {
		UserDTO response =  service.find(id);
		return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public ResponseEntity update(@PathVariable Integer id, @RequestBody ExpandedUserDTO update) {
		UserDTO response = service.update(id, update);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@GetMapping("/{id}/ride")
	public ResponseEntity passengerRides(@PathVariable Integer id, @RequestParam Integer page, Integer size, String sort, String from, String to){
		RideCount response = service.passengerRides(id, page, size, sort, from, to);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@GetMapping("/activate/{activationId}")
	public ResponseEntity activatePassenger(@PathVariable Integer activationId){
		return new ResponseEntity(HttpStatus.OK);
	}

}