package swp.medichor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp.medichor.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    
}
