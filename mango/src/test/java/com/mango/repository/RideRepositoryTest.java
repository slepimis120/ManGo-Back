package com.mango.repository;

import com.mango.model.*;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class RideRepositoryTest {

    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private FavoriteLocationRepository favoriteLocationRepository;

    @Before
    public void addRide() {
        RideLocation route = new RideLocation(new Location("Ledinacka 2", 45.248667F, 19.816370F), new Location("Kulturni Centar LAB", 45.24951752043822F, 19.825208148679025F));
        Passenger passenger = passengerRepository.getReferenceById(9);
        Driver driver = driverRepository.getReferenceById(1);
        Ride ride = new Ride(
                driver,
                Arrays.asList(route),
                Arrays.asList(passenger),
                Vehicle.Type.STANDARD, true, false, null);

        Date date = new Date();
        ride.setStartTime(date);
        ride = rideRepository.save(ride);
        Optional<Ride> rideO = rideRepository.findById(ride.getId());
    }

    @Test
    @DisplayName("findById [positive]")
    public void shouldFindById() {
        Optional<Ride> rideO = rideRepository.findById(9);
        assertThat(rideO).isNotEmpty();
    }

    @Test
    @DisplayName("ifRideExists [positive]")
    public void ifRideExists() throws ParseException {
        Boolean doesItExist = rideRepository.findById(9).orElse(null) != null;
        Assertions.assertEquals(doesItExist, true);
    }

    @Test
    @DisplayName("findActiveByDriver [positive]")
    public void findActiveByDriver() {
        Optional<Ride> ride = rideRepository.findActiveByDriver(1);
        if(ride.isPresent()){
            Assertions.assertEquals(ride.get().getVehicleType(), Vehicle.Type.STANDARD);
        }

    }

    @Test
    @DisplayName("findActiveByPassenger [positive]")
    public void findActiveByPassenger() {
        Ride ride = rideRepository.findActiveByPassenger(10);
        Assertions.assertEquals(ride.getVehicleType(), Vehicle.Type.STANDARD);
    }

    @Test
    @DisplayName("getFavLocationCount [positive]")
    public void getTotalCount() {
        Integer count = favoriteLocationRepository.getTotalCount(8);
        Assertions.assertEquals(count, 2);
    }
}
