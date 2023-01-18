package mango.controller;

import java.util.ArrayList;

import mango.dto.*;
import mango.model.Driver;
import mango.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mango.model.DriverDocument;
import mango.service.DriverService;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/driver")
public class DriverController {

	@Autowired
	DriverService service;
	
	
	@PostMapping
	public ResponseEntity insert(@RequestBody ExpandedUserDTO data) {
        UserDTO response =  service.insert(data);
		if(response == null){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with that email already exists exists!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping
	public ResponseEntity getArray(@RequestParam Integer page, Integer size) {
		UserResponseDTO response = service.getArray(page, size);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity find(@PathVariable Integer id) {
		UserDTO response =  service.find(id);
		if(response == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Driver does not exist!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public ResponseEntity update(@PathVariable Integer id, @RequestBody ExpandedUserDTO update) {
		UserDTO response = service.update(id, update);
		if(response == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Driver does not exist!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/{id}/documents")
	public ResponseEntity findDocuments(@PathVariable Integer id) {
		ArrayList<DriverDocumentDTO> response = service.findDocuments(id);
		if(response == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Driver does not exist!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping("/{id}/documents")
	public ResponseEntity insertDocument(@PathVariable Integer id, @RequestBody String name, String documentImage) {
        DriverDocumentDTO response =  service.insertDocument(id, name, documentImage);
		if(response == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Driver does not exist!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping("/document/{id}")
	public ResponseEntity deleteDocument(@PathVariable Integer id) {
		String response = service.deleteDocument(id);
		if(response == null) {
			return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
		}
        return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/{id}/working-hour")
	public ResponseEntity findWorkHours(@PathVariable Integer id, @RequestBody WorkHourQueryDTO workHourQueryDTO) {
		WorkHourResponseDTO  response = service.findWorkHours(id, workHourQueryDTO.getPage(), workHourQueryDTO.getSize(), workHourQueryDTO.getFrom(), workHourQueryDTO.getTo());
		return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@PostMapping("/{id_driver}/working-hour")
	public ResponseEntity insertWorkHour(@PathVariable Integer id_driver, @RequestBody String startHour) {
        WorkHourDTO response =  service.insertWorkHour(id_driver, startHour);
        return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@GetMapping("/working-hour/{id}")
	public ResponseEntity findWorkHour(@PathVariable Integer id) {
		WorkHourDTO response =  service.findWorkHour(id);
		return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@PutMapping(value="/working-hour/{workingHourId}")
	public ResponseEntity updateWorkHour(@PathVariable Integer workingHourId, @RequestBody String endHour) {
		WorkHourDTO response = service.updateWorkHour(workingHourId, endHour);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@PostMapping("{id}/vehicle")
	public ResponseEntity postVehicle(@PathVariable Integer id, @RequestBody Vehicle vehicle){
		Vehicle response = service.postVehicle(vehicle, id);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@GetMapping("{id}/vehicle")
	public ResponseEntity getVehicle(@PathVariable Integer id){
		Vehicle response = service.getVehicle(id);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@PutMapping("{id}/vehicle")
	public ResponseEntity changeVehicle(@PathVariable Integer id, @RequestBody Vehicle vehicle){
		Vehicle response = service.changeVehicle(id, vehicle);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@GetMapping("{id}/ride")
	public ResponseEntity getRides(@PathVariable Integer id, @RequestBody RideQueryDTO rideQueryDTO){
		RideCountDTO response = service.getRides(id, rideQueryDTO.getPage(), rideQueryDTO.getSize(), rideQueryDTO.getSort(), rideQueryDTO.getFrom(), rideQueryDTO.getTo());
		return new ResponseEntity(response, HttpStatus.OK);
	}
}

