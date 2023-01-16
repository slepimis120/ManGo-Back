package mango.repository;

import mango.model.Passenger;
import mango.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Integer> {

    @Query(value = "select r.* from Ride r, Ride_Passengers rs where (?1 = rs.passengers_id and r.id = rs.rides_id) and r.endTime IS NULL", nativeQuery = true)
    Ride findActiveByPassenger(Integer id);

    @Query(value = "select * from Ride where endTime IS NULL and driver IS NOT NULL and driver = ?1", nativeQuery = true)
    Ride findActiveByDriver(Integer id);

    @Query(value = "select * from Ride r where r.driver IS NOT NULL and r.driver.id = ?1", nativeQuery = true)
    List<Ride> findRidesByDriver(Integer driverId);

    @Query(value = "select r.* from Ride r, Ride_Passengers rs where ?1 = rs.passengers_id and r.id = rs.rides_id", nativeQuery = true)
    List<Ride> findRidesByPassenger(Integer id);

    @Query(value = "select r.* from Ride r where (r.driver IS NOT NULL and r.driver = ?1 and r.status = 'pending')", nativeQuery = true)
    Ride isDriverAssigned(Integer driverId);
}
