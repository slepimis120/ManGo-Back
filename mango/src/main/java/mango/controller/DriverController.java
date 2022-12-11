package mango.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mango.model.User;
import mango.service.interfaces.IUserService;

@RestController
@RequestMapping("/api/v1/driver")
public class DriverController {
	@Autowired
	IUserService service;
	
	@GetMapping
	public Collection<User> getUsers() {
		return service.getAll();
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