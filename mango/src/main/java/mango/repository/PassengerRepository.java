package mango.repository;

import mango.model.Activation;
import mango.model.Driver;
import mango.model.Passenger;
import mango.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

    @Query("select r.passengers from Ride r where (r.id = :rideId)")
    List<Passenger> getPassengerByRideId(Integer rideId);

    @Query(value="SELECT * FROM USERS WHERE TYPE = 'PASSENGER' OFFSET ?1 ROWS FETCH NEXT ?2 ROWS ONLY", nativeQuery = true)
    List<Passenger> fetchPassengerOffset(int o, int size);

    @Query(value="SELECT * FROM ACTIVATION A WHERE A.ID = ?1", nativeQuery = true)
    Activation getActivation(int id);

    @Query(value = "SELECT * FROM USERS WHERE TYPE = 'PASSENGER' AND EMAIL = ?1", nativeQuery = true)
    Passenger findByEmail(String email);
}
