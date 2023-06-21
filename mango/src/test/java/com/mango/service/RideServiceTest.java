package com.mango.service;

import com.mango.dto.CreateRideDTO;
import com.mango.model.*;
import com.mango.repository.DriverRepository;
import com.mango.repository.FavoriteLocationRepository;
import com.mango.repository.PassengerRepository;
import com.mango.repository.RideRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RideServiceTest {

    @Autowired
    @InjectMocks
    RideService rideService;

    @Mock
    private RideRepository rideRepository;
    @Mock
    private PassengerRepository passengerRepository;
    @Mock
    private DriverRepository driverRepository;
    @Mock
    private FavoriteLocationRepository favoriteLocationRepository;

    @Test
    public void shouldFindRideById() {

        RideLocation route = new RideLocation(new Location("Ledinacka 2", 45.248667F, 19.816370F), new Location("Kulturni Centar LAB", 45.24951752043822F, 19.825208148679025F));
        Passenger passenger = passengerRepository.getReferenceById(9);
        Driver driver = driverRepository.getReferenceById(1);
        Ride expectedRide = new Ride(
                driver,
                Arrays.asList(route),
                Arrays.asList(passenger),
                Vehicle.Type.STANDARD, true, false, null);

        Date date = new Date();
        expectedRide.setStartTime(date);
        Mockito.when(rideRepository.findById(10)).thenReturn(Optional.of(expectedRide));

        Ride actualRide = rideRepository.findById(10).orElse(null);

        verify(rideRepository, times(1)).findById(10);

        org.assertj.core.api.Assertions.assertThat(actualRide.getId()).isEqualTo(expectedRide.getId());
        org.assertj.core.api.Assertions.assertThat(actualRide.getStartTime()).isEqualTo(expectedRide.getStartTime());
        org.assertj.core.api.Assertions.assertThat(actualRide.getEndTime()).isEqualTo(expectedRide.getEndTime());
        org.assertj.core.api.Assertions.assertThat(actualRide.getScheduledTime()).isEqualTo(expectedRide.getScheduledTime());
        org.assertj.core.api.Assertions.assertThat(actualRide.getTotalCost()).isEqualTo(expectedRide.getTotalCost());
        org.assertj.core.api.Assertions.assertThat(actualRide.getLocations()).isEqualTo(expectedRide.getLocations());
        org.assertj.core.api.Assertions.assertThat(actualRide.getPassengers()).isEqualTo(expectedRide.getPassengers());
        org.assertj.core.api.Assertions.assertThat(actualRide.getVehicleType()).isEqualTo(expectedRide.getVehicleType());
        org.assertj.core.api.Assertions.assertThat(actualRide.isBabyTransport()).isEqualTo(expectedRide.isBabyTransport());
        org.assertj.core.api.Assertions.assertThat(actualRide.isPetTransport()).isEqualTo(expectedRide.isPetTransport());
        org.assertj.core.api.Assertions.assertThat(actualRide.getEstimatedTimeInMinutes()).isEqualTo(expectedRide.getEstimatedTimeInMinutes());
        org.assertj.core.api.Assertions.assertThat(actualRide.getStatus()).isEqualTo(expectedRide.getStatus());
        org.assertj.core.api.Assertions.assertThat(actualRide.getDriver()).isEqualTo(expectedRide.getDriver());
        Assertions.assertThat(actualRide.getRejection()).isEqualTo(expectedRide.getRejection());

    }

    @Test
    public void shouldFindDriversActiveRide() {
        RideLocation route = new RideLocation(new Location("Ledinacka 2", 45.248667F, 19.816370F), new Location("Kulturni Centar LAB", 45.24951752043822F, 19.825208148679025F));
        Passenger passenger = passengerRepository.getReferenceById(9);
        Driver driver = driverRepository.getReferenceById(1);
        Ride expectedRide = new Ride(
                driver,
                Arrays.asList(route),
                Arrays.asList(passenger),
                Vehicle.Type.STANDARD, true, false, null);

        Date date = new Date();
        expectedRide.setStartTime(date);
//        Mockito.when(rideService.findActiveByDriver(10)).thenReturn(Optional.of(expectedRide));

//        Ride actualRide = rideService.findById(10);
//
//        Assertions.assertThat(actualRide.getStartTime()).isEqualTo(expectedRide.getStartTime());
//        Assertions.assertThat(actualRide.getEndTime()).isEqualTo(expectedRide.getEndTime());
//        Assertions.assertThat(actualRide.getScheduledTime()).isEqualTo(expectedRide.getScheduledTime());
//        Assertions.assertThat(actualRide.getTotalCost()).isEqualTo(expectedRide.getTotalCost());
//        Assertions.assertThat(actualRide.getLocations()).isEqualTo(expectedRide.getLocations());
//        Assertions.assertThat(actualRide.getPassengers()).isEqualTo(expectedRide.getPassengers());
//        Assertions.assertThat(actualRide.getVehicleType()).isEqualTo(expectedRide.getVehicleType());
//        Assertions.assertThat(actualRide.isBabyTransport()).isEqualTo(expectedRide.isBabyTransport());
//        Assertions.assertThat(actualRide.isPetTransport()).isEqualTo(expectedRide.isPetTransport());
//        Assertions.assertThat(actualRide.getEstimatedTimeInMinutes()).isEqualTo(expectedRide.getEstimatedTimeInMinutes());
//        Assertions.assertThat(actualRide.getStatus()).isEqualTo(expectedRide.getStatus());
//        Assertions.assertThat(actualRide.getDriver()).isEqualTo(expectedRide.getDriver());
//        Assertions.assertThat(actualRide.getRejection()).isEqualTo(expectedRide.getRejection());

        verify(rideRepository, times(0)).findActiveByDriver(1);
    }

    @Test
    public void shouldDeleteFavoriteOrder() {
        Passenger passenger = new Passenger(20, "name", "surname", "sadas", "123465",
                "ana@gmail.com", "address", "pass", false);

        FavoriteLocations favoriteLocations = new FavoriteLocations(123, "name", new ArrayList<>(),
                Arrays.asList(passenger), Vehicle.Type.LUXURY, false, false);

//        Mockito.when(favoriteLocationRepository.findById(123)).thenReturn(Optional.of(favoriteLocations));
//
//        rideService.deleteFavoriteLocations(123);
//
//        verify(favoriteLocationRepository, times(1)).delete(favoriteLocations);
    }


}
