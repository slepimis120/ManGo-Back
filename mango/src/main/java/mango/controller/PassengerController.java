package mango.controller;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import mango.dto.*;
import mango.model.Activation;
import mango.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import mango.service.PassengerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/passenger")
public class PassengerController {
	@Autowired
	PassengerService service;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PermitAll
	@PostMapping
	public ResponseEntity insert(@RequestBody @Valid ExpandedUserDTO data) {
		data.setPassword(passwordEncoder.encode(data.getPassword()));
        Passenger response =  service.insertPassenger(data);
		if(response == null){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with that email already exists!");
		}
		UserDTO userDTO = new UserDTO(response);
		return ResponseEntity.status(HttpStatus.OK).body(userDTO);
	}

	@PermitAll
	@GetMapping("/activate/{activationId}")
	public ResponseEntity activatePassenger(@PathVariable Integer activationId){
		Activation activation = service.getActivation(activationId);
		if(activation == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Activation with entered id does not exist!");
		}
		Date currentDate = new Date();
		long day7 = 7L * 24 * 60 * 60 * 1000;
		boolean olderThan7 = currentDate.before(new Date((activation.getActivationSendDate().getTime() + day7)));
		if(!olderThan7){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Activation expired. Register again!");
		}
		service.activatePassenger(activation);
		return ResponseEntity.status(HttpStatus.OK).body("Successful account activation!");
	}

	@PreAuthorize("hasAuthority(\"ROLE_PASSENGER\")")
	@GetMapping
	public ResponseEntity getUsers(@RequestParam (required=false,name="page") Integer page, @RequestParam (required=false,name="size") Integer size) {
		System.out.println(size);
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
	public ResponseEntity update(@PathVariable Integer id, @RequestBody @Valid ExpandedUserDTO update) {
		if(BCrypt.checkpw(update.getPassword(), service.getById(id).getPassword())){
			UserDTO response = service.update(id, update);
			if(response == null){
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Passenger does not exist!");
			}
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong verification password!");
	}

	@PreAuthorize("hasAuthority(\"ROLE_PASSENGER\")")
	@RequestMapping(value="/passwordChange/{id}",method = RequestMethod.PUT)
	public ResponseEntity changePassword(@PathVariable Integer id, @RequestBody @Valid ChangePasswordDTO changePasswordDTO) {
		if(BCrypt.checkpw(changePasswordDTO.getVerification(), service.getById(id).getPassword())){
			changePasswordDTO.setNewPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
			UserDTO response = service.changePassword(id, changePasswordDTO);
			if(response == null){
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Passenger does not exist!");
			}
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong verification password!");
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