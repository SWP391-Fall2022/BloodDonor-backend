package swp.medichor.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.medichor.model.Donor;
import swp.medichor.repository.DonorRepository;

@Service
public class DonorService {
    @Autowired
    private DonorRepository donorRepository;

    public boolean registerDonor(Donor donor) {
        donorRepository.save(donor);
        return true;
    }
}
