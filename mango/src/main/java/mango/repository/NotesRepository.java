package mango.repository;

import mango.model.Note;
import mango.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotesRepository extends JpaRepository<Note, Integer> {
    @Query(value = "SELECT * FROM NOTE WHERE USERID = ? OFFSET ? ROWS FETCH NEXT ? ROWS ONLY", nativeQuery = true)
    List<Note> getNotes(int userId, int offset, int size);

}

