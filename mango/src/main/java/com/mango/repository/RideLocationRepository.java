package com.mango.repository;

import com.mango.model.RideLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideLocationRepository extends JpaRepository<RideLocation, Integer> {
}
