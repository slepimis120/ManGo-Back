package mango.repository;

import mango.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Integer> {

    @Query("select r.driver from Ride r where (r.id = :rideId)")
    Driver getDriverByRideId(Integer rideId);

    @Query(value = "SELECT * FROM USERS WHERE TYPE = 'DRIVER' OFFSET ? ROWS FETCH NEXT ? ROWS ONLY", nativeQuery = true)
    List<Driver> fetchDriversOffset(int offset, int size);

    @Query(value = "SELECT r.RATING FROM REVIEW r WHERE REVIEWTYPE = 'DRIVER' AND RIDEID IN (SELECT ID FROM RIDE WHERE DRIVER = ?)", nativeQuery = true)
    List<Integer> getAvgRating(Integer id);
}
