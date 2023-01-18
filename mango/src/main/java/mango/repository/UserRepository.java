package mango.repository;

import mango.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM USERS OFFSET ? ROWS FETCH NEXT ? ROWS ONLY", nativeQuery = true)
    List<User> getUsers(int offset, int size);
}
