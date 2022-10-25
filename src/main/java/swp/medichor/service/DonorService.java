package swp.medichor.service;

import java.sql.Timestamp;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swp.medichor.enums.DonateRegistrationStatus;
import swp.medichor.model.*;
import swp.medichor.model.compositekey.DonateRegistrationKey;
import swp.medichor.model.compositekey.EarnedRewardKey;
import swp.medichor.model.compositekey.LikeRecordKey;
import swp.medichor.model.request.DonateRegistrationRequest;
import swp.medichor.model.request.NumberOfRegistrationRequest;
import swp.medichor.model.request.QuestionRequest;
import swp.medichor.model.request.UpdateDonorRequest;
import swp.medichor.model.response.DonateRecordResponse;
import swp.medichor.model.response.DonateRegistrationResponse;
import swp.medichor.model.response.Response;
import swp.medichor.repository.*;
import swp.medichor.model.response.DonorResponse;
import swp.medichor.repository.CampaignRepository;
import swp.medichor.repository.DonateRecordRepository;
import swp.medichor.repository.DonateRegistrationRepository;
import swp.medichor.repository.DonorRepository;
import swp.medichor.repository.UserRepository;
import swp.medichor.utils.EmailPlatform;
import swp.medichor.utils.Random;
import swp.medichor.utils.Validator;

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
    @Autowired
    private LikeRecordRepository likeRecordRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private RewardRepository rewardRepository;
    @Autowired
    private EarnedRewardRepository earnedRewardRepository;
    @Autowired
    private CampaignService campaignService;
    @Autowired
    private EmailService emailService;

    private static final int LIMIT_REGISTRATION = 100;
    private final String FROM = "medichorvn@gmail.com";
    private final String SUBJECT = "BLOOD DONATION CAMPAIGN CHECK-IN VERIFICATION CODE";


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
            if (Validator.canCampaignRegistered(c, registrationReq.getRegisterDate())) {

                //Checking the number of registration of the time submitted
                if ((int)campaignService.getNumberOfRegistrationPerDay(
                        registrationReq.getCampaignId(),
                        new NumberOfRegistrationRequest(
                                registrationReq.getPeriod(),
                                registrationReq.getRegisterDate()
                        )).getBody() >= LIMIT_REGISTRATION)
                    throw new RuntimeException("Registration of this time has been full");

                // Delete cancelled campaign
                Optional<DonateRegistration> oldRegistration
                        = donateRegistrationRepository.findById_DonorIdAndId_CampaignId(donor.getUserId(), c.getId());
                oldRegistration.ifPresent(r -> {
                    if (r.getStatus() != DonateRegistrationStatus.CANCELLED) {
                        throw new RuntimeException("The user has registered the campaign");
                    } else {
                        donateRegistrationRepository.delete(r);
                    }
                });

                // Add
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
                donateRegistrationRepository.save(registration);

                //send mail the code to donor
                emailService.send(FROM, donor.getUser().getEmail(), SUBJECT, EmailPlatform.buildCheckinCodeEmail(
                        registration
                ));

            } else {
                throw new RuntimeException("Registration is not valid");
            }
        }, () -> {
            throw new IllegalArgumentException("Campaign not found");
        });
    }

    @Transactional
    public void updateDonateRegistration(int donorId, int campaignId, DonateRegistrationRequest req) {
        campaignRepository.findById(campaignId).ifPresentOrElse(campaign -> {
            if (Validator.canCampaignRegistered(campaign, req.getRegisterDate())) {
                if (donateRegistrationRepository.updateDonateRegistration(req.getRegisterDate(),
                        req.getPeriod(),
                        donorId,
                        campaignId) == 0) {
                    throw new RuntimeException("Cannot update the registration");
                }
            } else {
                throw new RuntimeException("Registration is not valid");
            }
        }, () -> {
            throw new RuntimeException("Campaign does not exist");
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

    public void cancelRegistration(int donorId, int campaignId) {
        donateRegistrationRepository.findById_DonorIdAndId_CampaignId(donorId, campaignId)
                .ifPresentOrElse(r -> {
                    if (r.getStatus() == DonateRegistrationStatus.NOT_CHECKED_IN) {
                        r.setStatus(DonateRegistrationStatus.CANCELLED);
                        donateRegistrationRepository.save(r);
                    } else {
                        throw new RuntimeException("Cannot cancel the registration");
                    }
                }, () -> {
                    throw new RuntimeException("Campaign does not exist");
                });
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

    public Response likeCampaign(User user, Integer campaignId) {
        Optional<Campaign> isExistCampaign = campaignRepository.findById(campaignId);
        if (isExistCampaign.isEmpty()) {
            return new Response(400, false, "ID not found");
        }
        Optional<LikeRecord> isExistLikeRecord
                = likeRecordRepository.findByCampaignIdAndDonorId(campaignId, user.getDonor().getUserId());
        if (isExistLikeRecord.isPresent()) {
            return new Response(400, false, "Already liked");
        }
        Campaign campaign = isExistCampaign.get();
        LikeRecord likeRecord = LikeRecord.builder()
                .id(new LikeRecordKey(null, null))
                .campaign(campaign)
                .donor(user.getDonor())
                .build();
        likeRecordRepository.save(likeRecord);
        return new Response(200, true, "Like successfully");
    }

    public Response addQuestion(User user, Integer campaignId, QuestionRequest request) {
        Optional<Campaign> isExistCampaign = campaignRepository.findById(campaignId);
        if (isExistCampaign.isEmpty()) {
            return new Response(400, false, "ID not found");
        }
        Campaign campaign = isExistCampaign.get();
        Question question = Question.builder()
                .donor(user.getDonor())
                .campaign(campaign)
                .askTime(new Timestamp(System.currentTimeMillis()))
                .question(request.getQuestion())
                .answer(null)
                .build();
        questionRepository.save(question);
        return new Response(200, true, "Add question successfully");
    }

    public int getPoints(int donorId) {
        int amountDonated = getTotalAmountOfBlood(donorId);

        List<EarnedReward> earned = earnedRewardRepository.findAllById_DonorId(donorId);

        // get used points for exchanging rewards
        int usedPoints = 0;
        for (EarnedReward reward : earned) {
            usedPoints += reward.getReward().getLevel();
        }

        return amountDonated - usedPoints;
    }

    public void claimReward(int donorId, int rewardId) {
        EarnedRewardKey id = new EarnedRewardKey(donorId, rewardId);
        rewardRepository.findById(rewardId).ifPresentOrElse(reward -> {
            donorRepository.findById(donorId).ifPresentOrElse(donor -> {
                if (reward.getStatus() == true
                        && reward.getAmount() > 0
                        && reward.getExpiredDate().compareTo(new Date(LocalDate.now().toEpochDay())) >= 0
                        && reward.getLevel() <= getPoints(donorId)
                        && !earnedRewardRepository.existsById(id)) {
                    earnedRewardRepository.save(EarnedReward.builder()
                            .id(id)
                            .donor(donor)
                            .reward(reward)
                            .receiveDate(new Date(System.currentTimeMillis()))
                            .build());
                } else {
                    throw new RuntimeException("Cannot claim the reward");
                }
            }, () -> {
                throw new RuntimeException("Donor ID does not exist");
            });
        }, () -> {
            throw new RuntimeException("Reward ID does not exist");
        });
    }
}
