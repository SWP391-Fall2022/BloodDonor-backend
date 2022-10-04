package swp.medichor.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp.medichor.enums.Role;
import swp.medichor.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.username = ?1 OR u.email = ?1 AND u.status = true")
    Optional<User> findByUsernameOrEmail(String value);
    @Query("SELECT u FROM User u WHERE u.username = ?1 AND u.email = ?2 AND u.status = true")
    Optional<User> findByUsernameAndEmail(String username, String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    @Query("SELECT u FROM User u WHERE u.district.id = ?1 AND u.role = ?2 AND u.status = true AND u.enabled = true")
    List<User> findByDistrictId(Integer districtId, Role role);
}
