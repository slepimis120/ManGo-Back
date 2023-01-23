package mango.repository;

import mango.model.Activation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActivationRepository extends JpaRepository<Activation, Integer> {

    @Query(value = "SELECT * FROM ACTIVATION WHERE PASSENGERID = ?1", nativeQuery = true)
    Activation getActivation(Integer id);

}
