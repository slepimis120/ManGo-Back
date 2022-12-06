package mango.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/v1")
public class MainController {
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "Spring Boot Works, " + new Date();
	}

}
