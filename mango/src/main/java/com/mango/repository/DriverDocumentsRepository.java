package com.mango.repository;

import com.mango.model.DriverDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DriverDocumentsRepository extends JpaRepository<DriverDocument, Integer> {
    @Query(value = "SELECT * FROM DRIVER_DOCUMENT WHERE DRIVERID = ?", nativeQuery = true)
    List<DriverDocument> findDriverDocuments(Integer id);
}
