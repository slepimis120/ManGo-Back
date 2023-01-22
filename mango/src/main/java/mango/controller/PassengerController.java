package mango.controller;

import mango.dto.ExpandedUserDTO;
import mango.dto.RideCountDTO;
import mango.dto.UserDTO;
import mango.dto.UserResponseDTO;
import mango.model.Activation;
import mango.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

import java.util.Date;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/passenger")
public class PassengerController {
	@Autowired
	PassengerService service;

	@PreAuthorize("permitAll()")
	@PostMapping
	public ResponseEntity insert(@RequestBody ExpandedUserDTO data) {
        Passenger response =  service.insertPassenger(data);
		if(response == null){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with that email already exists!");
		}
		Activation activation = new Activation(response, new Date(), false);
		UserDTO userDTO = new UserDTO(response);
		return ResponseEntity.status(HttpStatus.OK).body(userDTO);
	}

	@PreAuthorize("permitAll()")
	@GetMapping("/activate/{activationId}")
	public ResponseEntity activatePassenger(@PathVariable Integer activationId){
		Activation activation = service.getActivation(activationId);
		if(activation == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Activation with entered id does not exist!");
		}
		Date currentDate = new Date();
		long day7 = 7L * 24 * 60 * 60 * 1000;
		boolean olderThan7 = currentDate.before(new Date((activation.getActivationSendDate().getTime() + day7)));
		if(olderThan7){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Activation expired. Register again!");
		}
		service.activatePassenger(activation);
		return ResponseEntity.status(HttpStatus.OK).body("Successful account activation!");
	}

	@PreAuthorize("hasAuthority(\"ROLE_PASSENGER\")")
	@GetMapping
	public ResponseEntity getUsers(@RequestParam Integer page, Integer size) {
		UserResponseDTO response = service.getArray(page, size);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PreAuthorize("hasAuthority(\"ROLE_PASSENGER\")")
	@GetMapping("/{id}")
	public ResponseEntity findUser(@PathVariable Integer id) {
		UserDTO response =  service.find(id);
		if(response == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Passenger does not exist!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PreAuthorize("hasAuthority(\"ROLE_PASSENGER\")")
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public ResponseEntity update(@PathVariable Integer id, @RequestBody ExpandedUserDTO update) {
		UserDTO response = service.update(id, update);
		if(response == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Passenger does not exist!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PreAuthorize("hasAuthority(\"ROLE_PASSENGER\")")
	@GetMapping("/{id}/ride")
	public ResponseEntity getRides(@PathVariable Integer id, @RequestParam Integer page, Integer size, String sort, String from, String to){
		RideCountDTO response = service.passengerRides(id, page, size, sort, from, to);
		if(response == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Passenger does not exist!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}