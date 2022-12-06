package mango.controller;

import mango.model.Location;
import mango.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import mango.service.interfaces.IVehicleService;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    @Autowired
    IVehicleService service;

    @GetMapping("/{id}")
    public Vehicle findVehicle(@PathVariable Integer id) {
        return service.find(id);
    }

    @PostMapping
    public Vehicle insertVehicle(@RequestBody Vehicle vehicle){return service.insert(vehicle);}

    @PutMapping
    public Vehicle update(@RequestBody Vehicle vehicle) {
        return service.update(vehicle);
    }

    @PutMapping("/{id}/location")
    public void updateLocation(@RequestBody Location location, @PathVariable Integer id) {
        service.updateLocation(location, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @DeleteMapping
    public void deleteAll() {
        service.deleteAll();
    }

}
