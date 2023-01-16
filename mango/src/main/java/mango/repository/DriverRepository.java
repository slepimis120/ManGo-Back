package mango.repository;

import mango.model.Driver;
import mango.model.Review;
import mango.model.Ride;
import mango.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Integer> {

    @Query("select r.driver from Ride r where (r.id = :rideId)")
    Driver getDriverByRideId(Integer rideId);
}
