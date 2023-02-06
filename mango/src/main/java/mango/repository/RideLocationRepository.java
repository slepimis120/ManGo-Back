package mango.repository;

import mango.model.RideLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideLocationRepository extends JpaRepository<RideLocation, Integer> {
}
