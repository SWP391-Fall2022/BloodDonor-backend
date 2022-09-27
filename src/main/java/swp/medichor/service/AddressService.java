package swp.medichor.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.medichor.model.District;
import swp.medichor.repository.DistrictRepository;
import swp.medichor.repository.ProvinceRepository;

@Service
public class AddressService {
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private DistrictRepository districtRepository;

    public District getDistrictById(int districtId) {
        return districtRepository.findById(districtId).orElseThrow(() ->
                new IllegalStateException("District id not found")
        );
    }
}
