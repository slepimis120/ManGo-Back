package mango.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mango.dto.DriverDocumentDTO;
import mango.dto.ExpandedUserDTO;
import mango.dto.UserDTO;
import mango.dto.UserResponseDTO;
import mango.dto.WorkHourDTO;
import mango.dto.WorkHourResponseDTO;
import mango.model.DriverDocument;
import mango.service.DriverService;

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
	
	@DeleteMapping("/document/{document-id}")
	public ResponseEntity deleteDocument(@PathVariable Integer id) {
		DriverDocument response =  service.deleteDocument(id);
		if(response == null) {
			return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
		}
        return new ResponseEntity(HttpStatus.OK);
	}
	
	@GetMapping("/{id}/working-hour")
	public ResponseEntity findWorkHours(@PathVariable Integer id, @RequestParam Integer page, Integer size, String from, String to) {
		WorkHourResponseDTO  response = service.findWorkHours(id, page, size, from, to);
		return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@PostMapping("/{id_driver}/working-hour")
	public ResponseEntity insertWorkHour(@PathVariable Integer id_driver, @RequestBody Integer id, String start, String end ) {
        WorkHourDTO response =  service.insertWorkHour(id_driver, id, start, end);
        return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@GetMapping("/working-hour/{working-hour-id}")
	public ResponseEntity findWorkHour(@PathVariable Integer id) {
		WorkHourDTO response =  service.findWorkHour(id);
		return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@RequestMapping(value="/working-hour/{working-hour-id}",method = RequestMethod.PUT)
	public ResponseEntity updateWorkHour(@PathVariable Integer workingHourId, @RequestBody Integer id, String start, String end) {
		WorkHourDTO response = service.updateWorkHour(workingHourId, id, start, end);
		return new ResponseEntity(response, HttpStatus.OK);
	}
}

