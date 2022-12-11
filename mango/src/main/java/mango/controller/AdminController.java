package mango.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mango.service.interfaces.IUserService;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
	@Autowired
	IUserService service;

}