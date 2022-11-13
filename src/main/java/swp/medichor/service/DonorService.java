package swp.medichor.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import swp.medichor.model.request.UpdateDonorRequest;
import swp.medichor.model.response.CampaignResponse;
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
    private final String SUBJECT = "MÃ ĐIỂM DANH KHI THAM GIA CHIẾN DỊCH HIẾN MÁU";

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
                if ((int) campaignService.getNumberOfRegistrationPerDay(
                        registrationReq.getCampaignId(),
                        new NumberOfRegistrationRequest(
                                registrationReq.getPeriod(),
                                registrationReq.getRegisterDate()
                        )).getBody() >= LIMIT_REGISTRATION) {
                    throw new RuntimeException("Thời gian này đã đủ người đăng kí");
                }

                // The time between donations must be at least 12 weeks
                donateRecordRepository.findTopById_DonorIdAndStatusTrueOrderById_RegisteredDateDesc(donor.getUserId())
                        .ifPresent(r -> {
                            if (ChronoUnit.WEEKS.between(r.getId().getRegisteredDate().toLocalDate(), registrationReq.getRegisterDate()) < 12) {
                                throw new RuntimeException("Thời gian giữa 2 lần hiến máu phải ít nhất 12 tuần");
                            }
                        });

                // Contains a not_check_in registration
                if (!donateRegistrationRepository.findById_DonorIdAndId_CampaignIdAndStatus(donor.getUserId(),
                        c.getId(),
                        DonateRegistrationStatus.NOT_CHECKED_IN)
                        .isEmpty()) {
                    throw new RuntimeException("Tình nguyện viên đã đăng kí chiến dịch này");
                }

                donateRegistrationRepository.findById(new DonateRegistrationKey(
                        donor.getUserId(),
                        c.getId(),
                        registrationReq.getRegisterDate()))
                        .ifPresent(r -> {
                            // Cannot register the same day as checked_in
                            if (r.getStatus() == DonateRegistrationStatus.CHECKED_IN) {
                                throw new RuntimeException("Tình nguyện viên đã hiến máu vào ngày này");
                            } // Is canceled, delete to insert the new one
                            else {
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
                        .code(Integer.toString(Random.randomCode(100000, 999999)))
                        .build();
                donateRegistrationRepository.save(registration);

                //send mail the code to donor
                emailService.send(FROM, donor.getUser().getEmail(), SUBJECT, EmailPlatform.buildCheckinCodeEmail(
                        registration
                ));

            } else {
                throw new RuntimeException("Đơn đăng kí không hợp lệ");
            }
        }, () -> {
            throw new IllegalArgumentException("Không tìm thấy chiến dịch");
        });
    }

    @Transactional
    public void updateDonateRegistration(int donorId, int campaignId, LocalDate oldDate, DonateRegistrationRequest req) {
        donateRegistrationRepository.findById(new DonateRegistrationKey(
                donorId,
                campaignId,
                oldDate))
                .ifPresentOrElse(r -> {
                    if (Validator.canCampaignRegistered(r.getCampaign(), req.getRegisterDate())) {
                        // The time between donations must be at least 12 weeks
                        donateRecordRepository.findTopById_DonorIdAndStatusTrueOrderById_RegisteredDateDesc(donorId)
                                .ifPresent(record -> {
                                    if (ChronoUnit.WEEKS.between(record.getId().getRegisteredDate().toLocalDate(), req.getRegisterDate()) < 12) {
                                        throw new RuntimeException("Thời gian giữa 2 lần hiến máu phải ít nhất 12 tuần");
                                    }
                                });

                        // If registerDate is edited
                        if (!oldDate.equals(req.getRegisterDate())) {
                            Optional<DonateRegistration> oldReg = donateRegistrationRepository.findById(new DonateRegistrationKey(donorId, campaignId, req.getRegisterDate()));
                            if (oldReg.isPresent()) {
                                if (oldReg.get().getStatus() != DonateRegistrationStatus.CANCELLED) {
                                    throw new RuntimeException("Tình nguyện viên đã đăng kí vào ngày này");
                                }
                                // Delete canceled registration to edit
                                donateRegistrationRepository.delete(oldReg.get());
                            }
                        }

                        if (donateRegistrationRepository.updateDonateRegistration(req.getRegisterDate(),
                                req.getPeriod(),
                                donorId,
                                campaignId) == 0) {
                            throw new RuntimeException("Không thể chỉnh sửa đơn đăng kí");
                        }
                    } else {
                        throw new RuntimeException("Đơn đăng kí không hợp lệ");
                    }
                }, () -> {
                    throw new RuntimeException("Đơn đăng kí không tồn tại");
                });
    }

    @Transactional
    public List<DonateRegistrationResponse> getAllRegistrations(int donorId) {
        Optional<Donor> donor = donorRepository.findById(donorId);
        if (donor.isPresent()) {
            Set<DonateRegistration> registrations = donor.get().getRegistrations();
            return registrations.stream()
                    .sorted((r1, r2) -> r2.getId().getRegisteredDate()
                            .compareTo(r1.getId().getRegisteredDate()))
                    .map(r -> new DonateRegistrationResponse(r))
                    .collect(Collectors.toList());
        }
        throw new IllegalArgumentException("Không tìm thấy tình nguyện viên");
    }

    public void cancelRegistration(int donorId, int campaignId) {
        List<DonateRegistration> regs = donateRegistrationRepository.findById_DonorIdAndId_CampaignIdAndStatus(donorId, campaignId, DonateRegistrationStatus.NOT_CHECKED_IN);
        if (!regs.isEmpty()) {
            DonateRegistration reg = regs.get(0);
            reg.setStatus(DonateRegistrationStatus.CANCELLED);
            donateRegistrationRepository.save(reg);
        } else {
            throw new RuntimeException("Không tìm thấy chiến dịch");
        }
    }

    @Transactional
    public List<DonateRecordResponse> getAllDonations(int donorId) {
        Optional<Donor> donor = donorRepository.findById(donorId);
        if (donor.isPresent()) {
            Set<DonateRecord> records = donor.get().getRecord();
            return records.stream()
                    .sorted((r1, r2) -> r2.getId().getRegisteredDate()
                            .compareTo(r1.getId().getRegisteredDate()))
                    .map(r -> new DonateRecordResponse(r))
                    .collect(Collectors.toList());
        }
        throw new IllegalArgumentException("Không tìm thấy tình nguyện viên");
    }

    public Optional<DonateRecordResponse> getLatestonation(int donorId) {
        return donateRecordRepository.findTopById_DonorIdOrderById_RegisteredDateDesc(donorId)
                .map(r -> new DonateRecordResponse(r));
    }

    public int getTotalAmountOfBlood(int donorId) {
        Integer amount = donateRecordRepository.sumOfAmountByDonorId(donorId);
        return amount != null ? amount : 0;
    }

    public Response likeCampaign(User user, Integer campaignId) {
        Optional<Campaign> isExistCampaign = campaignRepository.findById(campaignId);
        if (isExistCampaign.isEmpty()) {
            return new Response(400, false, "Không tìm thấy chiến dịch");
        }
        Optional<LikeRecord> isExistLikeRecord
                = likeRecordRepository.findByCampaignIdAndDonorId(campaignId, user.getDonor().getUserId());
        if (isExistLikeRecord.isPresent()) {
            return new Response(400, false, "Đã yêu thích chiến dịch này");
        }
        Campaign campaign = isExistCampaign.get();
        LikeRecord likeRecord = LikeRecord.builder()
                .id(new LikeRecordKey(null, null))
                .campaign(campaign)
                .donor(user.getDonor())
                .build();
        likeRecordRepository.save(likeRecord);
        return new Response(200, true, "Đã yêu thích");
    }
    
    @Transactional
    public List<CampaignResponse> getLikedCampaigns(int userId) {
        Optional<Donor> donor = donorRepository.findById(userId);
        if (donor.isEmpty()) {
            throw new RuntimeException("Không tìm thấy donor");
        }
        return donor.get()
                .getLikeRecord()
                .stream()
                .map(r -> new CampaignResponse(r.getCampaign()))
                .filter(c -> c.isStatus() == true)
                .collect(Collectors.toList());
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
                    throw new RuntimeException("Không thể nhận mã khuyến mãi");
                }
            }, () -> {
                throw new RuntimeException("Không tìm thấy tình nguyện viên");
            });
        }, () -> {
            throw new RuntimeException("Không tìm thấy mã khuyến mãi");
        });
    }

    public Map<String, Object> getRegistrationStatus(int donorId, int campaignId) {
        Optional<Donor> donor = donorRepository.findById(donorId);
        if (donor.isPresent()) {
            Optional<Campaign> campaignOptional = campaignRepository.findById(campaignId);
            if (campaignOptional.isPresent()) {
                Campaign campaign = campaignOptional.get();
                List<DonateRegistration> registration = donateRegistrationRepository.findById_DonorIdAndId_CampaignIdAndStatus(donorId, campaignId, DonateRegistrationStatus.NOT_CHECKED_IN);
                boolean canRegister = campaign.getStatus() == true
                        && (campaign.getEndDate() == null
                        || (!campaign.getEndDate().isBefore(LocalDate.now())
                        && !campaign.getEndDate().isBefore(campaign.getStartDate())));
                boolean hasRegistered = !registration.isEmpty();

                // return: can campain be registered, is there any not_check_in registration
                Map<String, Object> res = new HashMap<>(3);
                res.put("canRegister", canRegister);
                res.put("hasRegistered", hasRegistered);
                return res;
            } else {
                throw new RuntimeException("Không tìm thấy chiến dịch");
            }
        } else {
            throw new RuntimeException("Không tìm thấy tình nguyện viên");
        }
    }

    public List<Map<String, Integer>> getTop5Donor(Date from, Date to) {
        return donorRepository.getTop5Donor(from, to);
    }
}
