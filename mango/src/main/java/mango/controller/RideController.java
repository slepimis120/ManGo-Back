package mango.controller;


import mango.model.Panic;
import mango.model.Rejection;
import mango.model.Ride;
import mango.service.interfaces.IRideService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ride")
public class RideController {

    @Autowired
    IRideService service;

    @PostMapping
    public Ride insertVehicle(@RequestBody Ride ride){return service.insert(ride);}

    @GetMapping("/{id}")
    public Ride findRide(@PathVariable Integer id) {
        return service.find(id);
    }
    @GetMapping("/driver/{driverId}/active")
    public Ride findByDriver(@PathVariable Integer driverId) throws Exception {
        return service.findByDriver(driverId);
    }

    @GetMapping("/passenger/{passengerId}/active")
    public Ride findByPassenger(@PathVariable Integer passengerId) {
        return service.findByPassenger(passengerId);
    }

    @PutMapping("/{id}/withdraw")
    public Ride cancelByPassenger(@PathVariable Integer id){return service.cancelByPassenger(id);}

    @PutMapping("/{id}/accept")
    public Ride accept(@PathVariable Integer id){return service.accept(id);}

    @PutMapping("/{id}/end")
    public Ride end(@PathVariable Integer id){return service.end(id);}

    @PutMapping("/{id}/cancel")
    public Ride cancelByDriver(@PathVariable Integer id, @RequestBody Rejection rejection){return service.cancelByDriver(id, rejection);}

    @PutMapping("/{id}/panic")
    public Panic panic(@PathVariable Integer id, @RequestBody Panic panic){return service.panic(id,panic);}
    }
