package swp.medichor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp.medichor.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    
}
