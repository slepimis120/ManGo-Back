package mango.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import mango.dto.*;
import mango.model.Vehicle;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import mango.service.DriverService;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/driver")
public class DriverController {

	@Autowired
	DriverService service;

	@PreAuthorize("hasAuthority(\"ROLE_ADMIN\")")
	@PostMapping
	public ResponseEntity insert(@RequestBody @Valid ExpandedUserDTO data) {
        UserDTO response =  service.insert(data);
		if(response == null){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with that email already exists exists!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PreAuthorize("hasAuthority(\"ROLE_ADMIN\")")
	@GetMapping
	public ResponseEntity getArray(@RequestParam Integer page, Integer size) {
		UserResponseDTO response = service.getArray(page, size);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PreAuthorize("hasAuthority(\"ROLE_ADMIN\")")
	@GetMapping("/{id}")
	public ResponseEntity find(@PathVariable Integer id) {
		UserDTO response =  service.find(id);
		if(response == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Driver does not exist!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PreAuthorize("hasAuthority(\"ROLE_ADMIN\")")
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public ResponseEntity update(@PathVariable Integer id, @RequestBody @Valid ExpandedUserDTO update) {
		UserDTO response = service.update(id, update);
		if(response == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Driver does not exist!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PreAuthorize("hasAuthority(\"ROLE_ADMIN\")")
	@GetMapping("/{id}/documents")
	public ResponseEntity findDocuments(@PathVariable Integer id) {
		ArrayList<DriverDocumentDTO> response = service.findDocuments(id);
		if(response == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Driver does not exist!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PreAuthorize("hasAuthority(\"ROLE_ADMIN\")")
	@PostMapping("/{id}/documents")
	public ResponseEntity insertDocument(@PathVariable Integer id, @RequestBody @Valid ShortDriverDocumentDTO document) {
        DriverDocumentDTO response =  service.insertDocument(id, document.getName(), document.getDocumentImage());
		if(response == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Driver does not exist!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PreAuthorize("hasAuthority(\"ROLE_ADMIN\")")
	@DeleteMapping("/document/{id}")
	public ResponseEntity deleteDocument(@PathVariable Integer id) {
		String response = service.deleteDocument(id);
		if(response == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Document does not exist!");
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
	}

	@PreAuthorize("hasAuthority(\"ROLE_ADMIN\")")
	@GetMapping("/{id}/working-hour")
	public ResponseEntity findWorkHours(@PathVariable Integer id,@RequestParam Integer page, Integer size, String from, String to) {
		WorkHourResponseDTO  response = service.findWorkHours(id, page, size, from, to);
		if(response == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Driver does not exist!");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PreAuthorize("hasAuthority(\"ROLE_DRIVER\")")
	@PostMapping("/{id_driver}/working-hour")
	public ResponseEntity insertWorkHour(@PathVariable Integer id_driver, @RequestBody @Valid @NotNull JSONObject start) {
        WorkHourDTO response =  service.insertWorkHour(id_driver, start.get("star").toString());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PreAuthorize("hasAuthority(\"ROLE_ADMIN\")")
	@GetMapping("/working-hour/{id}")
	public ResponseEntity findWorkHour(@PathVariable Integer id) {
		WorkHourDTO response =  service.findWorkHour(id);
		if(response == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Working hour does not exist!");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PreAuthorize("hasAuthority(\"ROLE_DRIVER\")")
	@PutMapping(value="/working-hour/{workingHourId}")
	public ResponseEntity updateWorkHour(@PathVariable Integer workingHourId, @RequestBody @Valid @NotNull JSONObject end) {
		WorkHourDTO response = service.updateWorkHour(workingHourId, end.get("end").toString());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PreAuthorize("hasAuthority(\"ROLE_ADMIN\")")
	@PostMapping("{id}/vehicle")
	public ResponseEntity postVehicle(@PathVariable Integer id, @RequestBody @Valid VehicleDTO vehicle){
		Vehicle response = service.postVehicle(vehicle, id);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PreAuthorize("hasAuthority(\"ROLE_ADMIN\")")
	@GetMapping("{id}/vehicle")
	public ResponseEntity getVehicle(@PathVariable Integer id){
		return service.getVehicle(id);
	}

	@PreAuthorize("hasAuthority(\"ROLE_ADMIN\")")
	@PutMapping("{id}/vehicle")
	public ResponseEntity changeVehicle(@PathVariable Integer id, @RequestBody @Valid VehicleDTO vehicle){
		VehicleDTO response = service.changeVehicle(id, vehicle);
		if(response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Driver does not exist!");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PreAuthorize("hasAuthority(\"ROLE_DRIVER\")")
	@GetMapping("{id}/ride")
	public ResponseEntity getRides(@PathVariable Integer id, @RequestParam Integer page, Integer size, String sort, String from, String to){
		RideCountDTO response = service.getRides(id, page, size, sort,from,to);
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

