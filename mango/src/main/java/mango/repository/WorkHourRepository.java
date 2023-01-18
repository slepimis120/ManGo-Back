package mango.repository;

import mango.model.WorkHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkHourRepository extends JpaRepository<WorkHour, Integer> {

    @Query(value = "SELECT * FROM WORK_HOUR WHERE(DRIVERID=? AND ENDTIME <= ? AND STARTTIME >= ?) OFFSET ? ROWS FETCH NEXT ? ROWS ONLY", nativeQuery = true)
    List<WorkHour> findWorkHours(Integer id,String endTime, String startTime, int offset, int size);

}
