package swp.medichor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp.medichor.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    
}
