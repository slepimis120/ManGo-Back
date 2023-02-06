package mango.repository;

import mango.model.Review;
import mango.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    @Query("select v from Vehicle v where (v.driver = :driverId)")
    Vehicle getVehicleByDriverId(Integer driverId);

    @Query(value = "SELECT * from Vehicle where (babyTransport = ?1 and passengerSeats > ?2 and petTransport = ?3 and vehicleType = ?4)", nativeQuery = true)
    List<Vehicle> findSuitableVehicles(boolean babyTransport, Integer passengerSeats, boolean petTransport, String vehicleType);
}
