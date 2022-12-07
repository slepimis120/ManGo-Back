package mango.controller;

import mango.model.PanicResponse;
import mango.service.interfaces.IPanicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/panic")
public class PanicController {

    @Autowired
    IPanicService service;

    @GetMapping
    public PanicResponse getAll(){return service.getAll();}

}
