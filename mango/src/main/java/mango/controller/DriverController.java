package mango.controller;

import java.util.ArrayList;

import mango.dto.*;
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
        return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity getArray(@RequestParam Integer page, Integer size) {
		UserResponseDTO response = service.getArray(page, size);
		return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity find(@PathVariable Integer id) {
		UserDTO response =  service.find(id);
		return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public ResponseEntity update(@PathVariable Integer id, @RequestBody ExpandedUserDTO update) {
		UserDTO response = service.update(id, update);
		return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}/documents")
	public ResponseEntity findDocuments(@PathVariable Integer id) {
		ArrayList<DriverDocumentDTO> response = service.findDocuments(id);
		return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@PostMapping("/{id}/documents")
	public ResponseEntity insertDocument(@PathVariable Integer id, @RequestBody String name, String documentImage) {
        DriverDocumentDTO response =  service.insertDocument(id, name, documentImage);
        return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/document/{id}")
	public ResponseEntity deleteDocument(@PathVariable Integer id) {
		DriverDocument response = service.deleteDocument(id);
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
	public ResponseEntity insertWorkHour(@PathVariable Integer id_driver, @RequestBody WorkHourDTO workhour) {
        WorkHourDTO response =  service.insertWorkHour(id_driver, workhour.getId(), workhour.getStart(), workhour.getEnd());
        return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@GetMapping("/working-hour/{id}")
	public ResponseEntity findWorkHour(@PathVariable Integer id) {
		WorkHourDTO response =  service.findWorkHour(id);
		return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@PutMapping(value="/working-hour/{workingHourId}")
	public ResponseEntity updateWorkHour(@PathVariable Integer workingHourId, @RequestBody WorkHourDTO workhour) {
		WorkHourDTO response = service.updateWorkHour(workingHourId, workhour.getId(), workhour.getStart(), workhour.getEnd());
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

