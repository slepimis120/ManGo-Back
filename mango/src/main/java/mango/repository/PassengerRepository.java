package mango.repository;

import mango.model.Driver;
import mango.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

    @Query("select r.passengers from Ride r where (r.id = :rideId)")
    List<Passenger> getPassengerByRideId(Integer rideId);

}
