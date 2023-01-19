package mango.repository;

import mango.model.Driver;
import mango.model.DriverDocument;
import mango.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DriverDocumentsRepository extends JpaRepository<DriverDocument, Integer> {
    @Query(value = "SELECT * FROM DRIVER_DOCUMENT WHERE DRIVERID = ?", nativeQuery = true)
    List<DriverDocument> findDriverDocuments(Integer id);
}
