package mango.controller;

import java.util.ArrayList;
import java.util.Date;

import lombok.Data;
import mango.dto.*;
import mango.model.Driver;
import mango.model.Vehicle;
import org.json.simple.JSONObject;
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
	public ResponseEntity insertDocument(@PathVariable Integer id, @RequestBody ShortDriverDocumentDTO document) {
        DriverDocumentDTO response =  service.insertDocument(id, document.getName(), document.getDocumentImage());
		if(response == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Driver does not exist!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping("/document/{id}")
	public ResponseEntity deleteDocument(@PathVariable Integer id) {
		String response = service.deleteDocument(id);
		if(response == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Document does not exist!");
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
	}
	
	@GetMapping("/{id}/working-hour")
	public ResponseEntity findWorkHours(@PathVariable Integer id,@RequestParam Integer page, Integer size, String from, String to) {
		WorkHourResponseDTO  response = service.findWorkHours(id, page, size, from, to);
		if(response == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Driver does not exist!");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping("/{id_driver}/working-hour")
	public ResponseEntity insertWorkHour(@PathVariable Integer id_driver, @RequestBody JSONObject start) {
        WorkHourDTO response =  service.insertWorkHour(id_driver, start.get("star").toString());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/working-hour/{id}")
	public ResponseEntity findWorkHour(@PathVariable Integer id) {
		WorkHourDTO response =  service.findWorkHour(id);
		if(response == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Working hour does not exist!");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PutMapping(value="/working-hour/{workingHourId}")
	public ResponseEntity updateWorkHour(@PathVariable Integer workingHourId, @RequestBody JSONObject end) {
		WorkHourDTO response = service.updateWorkHour(workingHourId, end.get("end").toString());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping("{id}/vehicle")
	public ResponseEntity postVehicle(@PathVariable Integer id, @RequestBody Vehicle vehicle){
		Vehicle response = service.postVehicle(vehicle, id);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("{id}/vehicle")
	public ResponseEntity getVehicle(@PathVariable Integer id){
		return service.getVehicle(id);
	}

	@PutMapping("{id}/vehicle")
	public ResponseEntity changeVehicle(@PathVariable Integer id, @RequestBody VehicleDTO vehicle){
		VehicleDTO response = service.changeVehicle(id, vehicle);
		if(response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Driver does not exist!");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("{id}/ride")
	public ResponseEntity getRides(@PathVariable Integer id, @RequestParam Integer page, Integer size, String sort, String from, String to){
		RideCountDTO response = service.getRides(id, page, size, sort,from,to);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}

