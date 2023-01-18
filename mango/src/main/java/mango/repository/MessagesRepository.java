package mango.repository;

import mango.model.Note;
import mango.model.UserMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessagesRepository extends JpaRepository<UserMessage, Integer> {
    @Query(value = "SELECT * FROM USER_MESSAGE WHERE SENDERID = ? OR RECEIVERID = ?", nativeQuery = true)
    List<UserMessage> getUserMessages(int id, int id_repeated);
}
