package swp.medichor.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swp.medichor.enums.DonateRegistrationStatus;
import swp.medichor.model.Campaign;
import swp.medichor.model.DonateRegistration;
import swp.medichor.model.Donor;
import swp.medichor.model.compositekey.DonateRegistrationKey;
import swp.medichor.model.request.DonateRegistrationRequest;
import swp.medichor.repository.CampaignRepository;
import swp.medichor.utils.Random;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    @Transactional
    public void registerDonor(int campaignId, Donor donor, DonateRegistrationRequest registrationReq) {
        Optional<Campaign> campaign = campaignRepository.findById(campaignId);
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
}
