package mango.controller;


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

    @GetMapping("/active/{driverId}")
    public Ride findByDriver(@PathVariable Integer driverId) {
        return service.findByDriver(driverId);
    }

    @GetMapping("/active/{passengerId}")
    public Ride findByPassenger(@PathVariable Integer passengerId) {
        return service.findByPassenger(passengerId);
    }

    @PutMapping("/{id}")
    public void cancelByPassenger(@RequestBody Integer id){service.cancelByPassenger(id);}

    @PutMapping("/{id}/accept")
    public Ride accept(@RequestBody Integer id){return service.accept(id);}

    @PutMapping("/{id}/end")
    public Ride end(@RequestBody Integer id){return service.end(id);}

    @PutMapping("/{id}/cancel")
    public Ride cancelByDriver(@RequestBody Integer id, String reason){return service.cancelByDriver(id, reason);}

}
