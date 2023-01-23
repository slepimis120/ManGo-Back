package mango.repository;

import mango.model.Activation;
import mango.model.Ride;
import mango.model.User;
import mango.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select * from Users where email = ?1", nativeQuery = true)
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM USERS OFFSET ? ROWS FETCH NEXT ? ROWS ONLY", nativeQuery = true)
    List<User> getUsers(int offset, int size);

}
