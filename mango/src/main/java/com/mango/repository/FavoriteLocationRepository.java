package com.mango.repository;

import com.mango.model.FavoriteLocations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteLocationRepository extends JpaRepository<FavoriteLocations, Integer> {

    @Query(value = "select COUNT(*) from FAVORITE_LOCATIONS_PASSENGERS where PASSENGERS_ID = ?1 and FAVORITE_LOCATIONS_ID in (SELECT ID from FAVORITE_LOCATIONS where ISDELETED = false)", nativeQuery = true)
    Integer getTotalCount(Integer id);

    @Query(value = "select r.* from FAVORITE_LOCATIONS r where r.id IN (SELECT id FROM FAVORITE_LOCATIONS_PASSENGERS where PASSENGERS_ID = ?1)", nativeQuery = true)
    List<FavoriteLocations> getByUserId(Integer id);
}
