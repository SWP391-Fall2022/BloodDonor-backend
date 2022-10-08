package swp.medichor.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swp.medichor.enums.DonateRegistrationStatus;
import swp.medichor.model.Campaign;
import swp.medichor.model.District;
import swp.medichor.model.DonateRecord;
import swp.medichor.model.DonateRegistration;
import swp.medichor.model.Donor;
import swp.medichor.model.compositekey.DonateRegistrationKey;
import swp.medichor.model.request.DonateRegistrationRequest;
import swp.medichor.model.request.UpdateDonorRequest;
import swp.medichor.model.response.DonateRecordResponse;
import swp.medichor.model.response.DonateRegistrationResponse;
import swp.medichor.model.response.DonorResponse;
import swp.medichor.repository.CampaignRepository;
import swp.medichor.repository.DonateRecordRepository;
import swp.medichor.repository.DonateRegistrationRepository;
import swp.medichor.repository.DonorRepository;
import swp.medichor.repository.UserRepository;
import swp.medichor.utils.Random;

@Service
public class DonorService {

    @Autowired
    private DonorRepository donorRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DonateRegistrationRepository donateRegistrationRepository;
    @Autowired
    private DonateRecordRepository donateRecordRepository;
    @Autowired
    private CampaignRepository campaignRepository;

    public boolean registerDonor(Donor donor) {
        donorRepository.save(donor);
        return true;
    }
    
    public List<DonorResponse> getAll() {
        return donorRepository.findAll()
                .stream()
                .map(donor -> new DonorResponse(donor))
                .collect(Collectors.toList());
    }

    public Optional<Donor> findById(int id) {
        return donorRepository.findById(id);
    }

    public void updateDonor(Donor donor, UpdateDonorRequest updatedDonor) {
        donor.setName(updatedDonor.getName());
        donor.setBirthday(updatedDonor.getBirthday());
        donor.setSex(updatedDonor.getSex());
        donor.setIdentityNum(updatedDonor.getIdentityNum());
        donor.setAvatar(updatedDonor.getAvatar());
        donor.setBloodType(updatedDonor.getBloodType());
        donor.setAnamnesis(updatedDonor.getAnamnesis());
        donor.getUser().setPhone(updatedDonor.getUser().getPhone());
        donor.getUser().setDistrict(District.builder().id(updatedDonor.getUser().getDistrictId()).build());
        donor.getUser().setAddressDetails(updatedDonor.getUser().getAddressDetails());
        userRepository.save(donor.getUser());
    }

    public long countRegisteredCampaigns(int donorId) {
        return donateRegistrationRepository.countById_DonorId(donorId);
    }

    public long countParticipatedCampaigns(int donorId) {
        return donateRecordRepository.countById_DonorIdAndStatusTrue(donorId);
    }

    @Transactional
    public void registerDonor(Donor donor, DonateRegistrationRequest registrationReq) {
        Optional<Campaign> campaign = campaignRepository.findById(registrationReq.getCampaignId());
        campaign.ifPresentOrElse(c -> {
            DonateRegistration registration = DonateRegistration.builder()
                    .id(new DonateRegistrationKey(
                            null,
                            null,
                            registrationReq.getRegisterDate()))
                    .donor(donor)
                    .campaign(c)
                    .status(DonateRegistrationStatus.NOT_CHECKED_IN)
                    .period(registrationReq.getPeriod())
                    .code(Integer.toString(Random.randomCode(100000000, 999999999)))
                    .build();
            c.getRegistrations().add(registration);
            campaignRepository.save(c);
        }, () -> {
            throw new IllegalArgumentException("Campaign not found");
        });
    }

    @Transactional
    public Set<DonateRegistrationResponse> getAllRegistrations(int donorId) {
        Optional<Donor> donor = donorRepository.findById(donorId);
        if (donor.isPresent()) {
            Set<DonateRegistration> registrations = donor.get().getRegistrations();
            return registrations.stream()
                    .map(r -> new DonateRegistrationResponse(r))
                    .collect(Collectors.toSet());
        }
        throw new IllegalArgumentException("Donor not found");
    }

    @Transactional
    public Set<DonateRecordResponse> getAllDonations(int donorId) {
        Optional<Donor> donor = donorRepository.findById(donorId);
        if (donor.isPresent()) {
            Set<DonateRecord> records = donor.get().getRecord();
            return records.stream()
                    .map(r -> new DonateRecordResponse(r))
                    .collect(Collectors.toSet());
        }
        throw new IllegalArgumentException("Donor not found");
    }

    public int getTotalAmountOfBlood(int donorId) {
        Integer amount = donateRecordRepository.sumOfAmountByDonorId(donorId);
        return amount != null ? amount : 0;
    }
}
