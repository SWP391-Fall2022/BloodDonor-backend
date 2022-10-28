package swp.medichor.repository;

import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp.medichor.model.Donor;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Integer> {

    @Query(value = "SELECT TOP 5 tbl.DonorId, COUNT(tbl.DonorId) AS Number, SUM(tbl.Amount) AS TotalAmount\n"
            + "FROM (SELECT DonorId, Amount FROM DonateRecord WHERE status=1) AS tbl\n"
            + "GROUP BY tbl.DonorId ORDER BY Number DESC, TotalAmount DESC", nativeQuery = true)
    List<Map<Integer, Integer>> getTop5Donor();
}
