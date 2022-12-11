package mango.controller;

import mango.dto.LocationDTO;
import mango.mapper.LocationDTOMapper;
import mango.model.Location;
import mango.model.Vehicle;
import mango.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mango.service.interfaces.IVehicleService;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    @Autowired
    VehicleService service;

    @PutMapping("/{id}/location")
    public ResponseEntity<Boolean> updateLocation(@RequestBody LocationDTO location, @PathVariable Integer id) {
        LocationDTOMapper mapper = new LocationDTOMapper(new ModelMapper());
        Location newLocation = mapper.fromDTOtoLocation(location);
        boolean response = service.updateLocation(newLocation, id);
        return new ResponseEntity<Boolean>(response, HttpStatus.NO_CONTENT);
    }
}