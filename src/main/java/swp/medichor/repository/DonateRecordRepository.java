package swp.medichor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp.medichor.model.DonateRecord;
import swp.medichor.model.compositekey.DonateRecordKey;

@Repository
public interface DonateRecordRepository extends JpaRepository<DonateRecord, DonateRecordKey> {
    long countById_DonorIdAndStatusTrue(int donorId);
}
