package mango.repository;

import mango.model.FavoriteLocations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteLocationRepository extends JpaRepository<FavoriteLocations, Integer> {

    @Query(value = "select COUNT(*) from USERS_FAVORITE_LOCATIONS where PASSENGERS_ID = ?1", nativeQuery = true)
    Integer getTotalCount(Integer id);

    @Query(value = "select r.* from FAVORITE_LOCATIONS r where r.id IN (SELECT id FROM USERS_FAVORITE_LOCATIONS where PASSENGERS_ID = ?1)", nativeQuery = true)
    List<FavoriteLocations> getByUserId(Integer id);
}
