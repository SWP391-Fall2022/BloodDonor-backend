package swp.medichor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp.medichor.model.VerificationCode;

import java.util.Optional;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Integer> {

    @Query("select c from VerificationCode c where c.userId = ?1")
    Optional<VerificationCode> findByUserId(Integer userId);

    @Query("SELECT c from VerificationCode c WHERE c.code = ?1 AND c.confirmed = false")
    Optional<VerificationCode> findByCode(int code);
}
