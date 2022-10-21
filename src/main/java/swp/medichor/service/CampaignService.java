package swp.medichor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.medichor.enums.Approve;
import swp.medichor.enums.DonateRegistrationStatus;
import swp.medichor.enums.Period;
import swp.medichor.enums.Role;
import swp.medichor.model.*;
import swp.medichor.model.compositekey.DonateRecordKey;
import swp.medichor.model.request.CreateCampaignRequest;
import swp.medichor.model.request.DonateRecordRequest;
import swp.medichor.model.request.NumberOfRegistrationRequest;
import swp.medichor.model.response.CampaignResponse;
import swp.medichor.model.response.DonateRegistrationResponse;
import swp.medichor.model.response.ParticipatedDonorResponse;
import swp.medichor.model.response.Response;
import swp.medichor.repository.*;
import swp.medichor.utils.EmailPlatform;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CampaignService {
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private DonorRepository donorRepository;
    @Autowired
    private CampaignRepository campaignRepository;
    @Autowired
    private DonateRegistrationRepository donateRegistrationRepository;
    @Autowired
    private DonateRecordRepository donateRecordRepository;
    @Autowired
    private LikeRecordRepository likeRecordRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    private final String FROM = "nvtien1602.forwork@gmail.com";
    private final String SUBJECT = "PLEASE JOIN IN THE BLOOD DONATION CAMPAIGN IF POSSIBLE";

    public Response createCampaign(Organization organization, CreateCampaignRequest request) {
        if (!organization.getUser().getStatus() || !organization.getUser().getEnabled()
                || organization.getApprove().equals(Approve.PENDING) || organization.getApprove().equals(Approve.REJECTED)) {
            return new Response(403, false, "The account is disabled or unverified");
        }

        if (request.isEmergency()) {
            request.setStartDate(LocalDate.now());
            request.setEndDate(null);
        }
        else {
            if (request.getEndDate() == null)
                return new Response(400, false, "Normal campaign requires end date.");
            if (request.getStartDate().isAfter(request.getEndDate()))
                return new Response(400, false, "Start date can not be after end date");
            if (request.getEndDate().isBefore(LocalDate.now()))
                return new Response(400, false, "End date can not be before today");
            if (request.getEndDate().minusDays(84).compareTo(request.getStartDate()) > 0)
                return new Response(400, false, "Normal campaign can not last for more than 12 weeks");
        }

        Campaign campaign = Campaign.builder()
                .name(request.getName())
                .images(request.getImages())
                .description(request.getDescription())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .emergency(request.isEmergency())
                .bloodTypes(request.getBloodTypes())
                .status(true)
                .district(District.builder().id(request.getDistrictId()).build())
                .addressDetails(request.getAddressDetails())
                .organization(organization)
                .build();
        campaign = campaignRepository.save(campaign);

        //send email to all donors in the same district
        if (request.isSendMail()) {
            List<User> listOfDonors = userService.getAllDonorsByDistrictId(request.getDistrictId());
            for (User user : listOfDonors) {
                emailService.send(FROM, user.getEmail(), SUBJECT, EmailPlatform.buildJoinInCampaignEmail(
                        user.getDonor().getName(),
                        campaign.getName()
                ));
            }
        }

        return new Response(200, true, "Create campaign successfully");
    }

    @Transactional
    public Response updateCampaign(User user, Integer campaignId, CreateCampaignRequest request) {
        Optional<Campaign> isExistCampaign = campaignRepository.findById(campaignId);
        if (isExistCampaign.isEmpty())
            return new Response(400, false, "ID not found");
        Campaign campaign = isExistCampaign.get();
        if (!campaign.getOrganization().getUserId().equals(user.getId())) {
            return new Response(403, false, "You have no right to update other org's campaign");
        }

        if (request.isEmergency()) {
            request.setStartDate(LocalDate.now());
            request.setEndDate(null);
        }
        else {
            if (request.getEndDate() == null)
                return new Response(400, false, "Normal campaign requires end date.");
            if (request.getStartDate().isAfter(request.getEndDate()))
                return new Response(400, false, "Start date can not be after end date");
            if (request.getEndDate().isBefore(LocalDate.now()))
                return new Response(400, false, "End date can not be before today");
            if (request.getEndDate().minusDays(84).compareTo(request.getStartDate()) > 0)
                return new Response(400, false, "Normal campaign can not last for more than 12 weeks");
        }

        campaign.setName(request.getName());
        campaign.setImages(request.getImages());
        campaign.setDescription(request.getDescription());
        campaign.setStartDate(request.getStartDate());
        campaign.setEndDate(request.getEndDate());
        campaign.setEmergency(request.isEmergency());
        campaign.setBloodTypes(request.getBloodTypes());
        campaign.setDistrict(District.builder().id(request.getDistrictId()).build());
        campaign.setAddressDetails(request.getAddressDetails());

        List<DonateRegistration> listOfOutdatedRegistration = new ArrayList<>();
        if (request.isEmergency()) {
            listOfOutdatedRegistration = donateRegistrationRepository.findUrgentByCampaignIdAndOutDate(
                    campaignId,
                    Date.valueOf(request.getStartDate()),
                    DonateRegistrationStatus.NOT_CHECKED_IN
            );
        }
        else {
            listOfOutdatedRegistration = donateRegistrationRepository.findNormalByCampaignIdAndOutDate(
                    campaignId,
                    Date.valueOf(request.getStartDate()),
                    Date.valueOf(request.getEndDate()),
                    DonateRegistrationStatus.NOT_CHECKED_IN
            );
        }
        for (DonateRegistration outDatedRegistration : listOfOutdatedRegistration) {
            outDatedRegistration.setStatus(DonateRegistrationStatus.CANCELLED);
            emailService.send(FROM, outDatedRegistration.getDonor().getUser().getEmail(), SUBJECT,
                    EmailPlatform.buildChangeCampaignEmail(
                    outDatedRegistration.getDonor().getName(),
                    campaign.getName()
            ));
        }

        return new Response(200, true, "Update successfully");
    }

    public Response readOneCampaign(Integer campaignId) {
        Optional<Campaign> isExistCampaign = campaignRepository.findById(campaignId);
        if (isExistCampaign.isEmpty())
            return new Response(400, false, "ID not found");
        Campaign campaign = isExistCampaign.get();
        CampaignResponse campaignInfo = new CampaignResponse(
                campaign.getId(),
                campaign.getName(),
                campaign.getImages(),
                campaign.getDescription(),
                campaign.getStartDate(),
                campaign.getEndDate(),
                campaign.getEmergency(),
                campaign.getBloodTypes(),
                campaign.getDistrict().getId(),
                campaign.getAddressDetails(),
                campaign.getOrganization().getName()
        );
        return new Response(200, true, campaignInfo);
    }

    @Transactional
    public Response deleteCampaign(User user, Integer campaignId) {
        Optional<Campaign> isExistCampaign = campaignRepository.findById(campaignId);
        if (isExistCampaign.isEmpty())
            return new Response(400, false, "ID not found");
        Campaign campaign = isExistCampaign.get();
        if (user.getRole().equals(Role.ADMIN)) {
            campaign.setStatus(false);
        }
        else if (user.getRole().equals(Role.ORGANIZATION)) {
            if (!campaign.getOrganization().getUserId().equals(user.getId())) {
                return new Response(403, false, "You have no right to delete other org's campaign");
            }
            campaign.setStatus(false);
        }
        List<DonateRegistration> listOfOutdatedRegistration = new ArrayList<>();
        if (campaign.getStartDate().compareTo(LocalDate.now()) >= 0) {
            listOfOutdatedRegistration = donateRegistrationRepository.findAllRegistration(
                    campaignId,
                    DonateRegistrationStatus.CANCELLED
            );
        }
        else {
            listOfOutdatedRegistration = donateRegistrationRepository.findNormalByCampaignIdAndOutDate(
                    campaignId,
                    Date.valueOf(campaign.getStartDate()),
                    Date.valueOf(LocalDate.now().minusDays(1)),
                    DonateRegistrationStatus.NOT_CHECKED_IN
            );
        }
        //send emails
        for (DonateRegistration outDatedRegistration : listOfOutdatedRegistration) {
            outDatedRegistration.setStatus(DonateRegistrationStatus.CANCELLED);
            emailService.send(FROM, outDatedRegistration.getDonor().getUser().getEmail(), SUBJECT,
                    EmailPlatform.buildCloseCampaignEmail(
                            outDatedRegistration.getDonor().getName(),
                            campaign.getName()
                    ));
        }

        return new Response(200, true, "Delete successfully");
    }

    @Transactional
    public Response closeCampaign(User user, Integer campaignId) {
        Optional<Campaign> isExistCampaign = campaignRepository.findById(campaignId);
        if (isExistCampaign.isEmpty())
            return new Response(400, false, "ID not found");
        Campaign campaign = isExistCampaign.get();
        if (!campaign.getOrganization().getUserId().equals(user.getId())) {
            return new Response(403, false, "You have no right to close other org's campaign");
        }

        List<DonateRegistration> listOfOutdatedRegistration = new ArrayList<>();
        if (campaign.getStartDate().compareTo(LocalDate.now()) >= 0) {
            campaign.setEndDate(campaign.getStartDate().minusDays(1));
            listOfOutdatedRegistration = donateRegistrationRepository.findAllRegistration(
                    campaignId,
                    DonateRegistrationStatus.CANCELLED
            );
        }
        else {
            campaign.setEndDate(LocalDate.now().minusDays(1));
            listOfOutdatedRegistration = donateRegistrationRepository.findNormalByCampaignIdAndOutDate(
                    campaignId,
                    Date.valueOf(campaign.getStartDate()),
                    Date.valueOf(campaign.getEndDate()),
                    DonateRegistrationStatus.NOT_CHECKED_IN
            );
        }
        //send emails
        for (DonateRegistration outDatedRegistration : listOfOutdatedRegistration) {
            outDatedRegistration.setStatus(DonateRegistrationStatus.CANCELLED);
            emailService.send(FROM, outDatedRegistration.getDonor().getUser().getEmail(), SUBJECT,
                    EmailPlatform.buildCloseCampaignEmail(
                            outDatedRegistration.getDonor().getName(),
                            campaign.getName()
                    ));
        }

        return new Response(200, true, "Close successfully");
    }

    public Response getAllActiveCampaigns() {
        List<Campaign> listActiveCampaigns = new ArrayList<>();
        List<CampaignResponse> listActiveCampaignsInfo = new ArrayList<>();
        listActiveCampaigns = campaignRepository.findAllActiveCampaigns(LocalDate.now());
        for (Campaign campaign : listActiveCampaigns) {
            CampaignResponse campaignInfo = new CampaignResponse(
                    campaign.getId(),
                    campaign.getName(),
                    campaign.getImages(),
                    campaign.getDescription(),
                    campaign.getStartDate(),
                    campaign.getEndDate(),
                    campaign.getEmergency(),
                    campaign.getBloodTypes(),
                    campaign.getDistrict().getId(),
                    campaign.getAddressDetails(),
                    campaign.getOrganization().getName()
            );
            listActiveCampaignsInfo.add(campaignInfo);
        }
        return new Response(200, true, listActiveCampaignsInfo);
    }

    public Response getAllActiveCampaignsByOrganizationId(Organization organization) {
        if (!organization.getUser().getStatus() || !organization.getUser().getEnabled()
                || organization.getApprove().equals(Approve.PENDING) || organization.getApprove().equals(Approve.REJECTED)) {
            return new Response(403, false, "The account is disabled or unverified");
        }

        List<Campaign> listActiveCampaigns = new ArrayList<>();
        List<CampaignResponse> listActiveCampaignsInfo = new ArrayList<>();
        listActiveCampaigns = campaignRepository.findAllActiveCampaignsByOrganizationId(organization.getUserId(),
                LocalDate.now());
        for (Campaign campaign : listActiveCampaigns) {
            CampaignResponse campaignInfo = new CampaignResponse(
                    campaign.getId(),
                    campaign.getName(),
                    campaign.getImages(),
                    campaign.getDescription(),
                    campaign.getStartDate(),
                    campaign.getEndDate(),
                    campaign.getEmergency(),
                    campaign.getBloodTypes(),
                    campaign.getDistrict().getId(),
                    campaign.getAddressDetails(),
                    campaign.getOrganization().getName()
            );
            listActiveCampaignsInfo.add(campaignInfo);
        }
        return new Response(200, true, listActiveCampaignsInfo);
    }

    public Response getAllCampaigns() {
        List<Campaign> listCampaigns = campaignRepository.findAllCampaigns();
        List<CampaignResponse> listCampaignsInfo = new ArrayList<>();
        for (Campaign campaign : listCampaigns) {
            CampaignResponse campaignInfo = new CampaignResponse(
                    campaign.getId(),
                    campaign.getName(),
                    campaign.getImages(),
                    campaign.getDescription(),
                    campaign.getStartDate(),
                    campaign.getEndDate(),
                    campaign.getEmergency(),
                    campaign.getBloodTypes(),
                    campaign.getDistrict().getId(),
                    campaign.getAddressDetails(),
                    campaign.getOrganization().getName()
            );
            listCampaignsInfo.add(campaignInfo);
        }
        return new Response(200, true, listCampaignsInfo);
    }

    public Response getAllCampaignsByOrganizationId(Organization organization) {
        if (!organization.getUser().getStatus() || !organization.getUser().getEnabled()
                || organization.getApprove().equals(Approve.PENDING) || organization.getApprove().equals(Approve.REJECTED)) {
            return new Response(403, false, "The account is disabled or unverified");
        }

        List<Campaign> listCampaigns = campaignRepository.findAllCampaignsByOrganizationId(organization.getUserId());
        List<CampaignResponse> listCampaignsInfo = new ArrayList<>();
        for (Campaign campaign : listCampaigns) {
            CampaignResponse campaignInfo = new CampaignResponse(
                    campaign.getId(),
                    campaign.getName(),
                    campaign.getImages(),
                    campaign.getDescription(),
                    campaign.getStartDate(),
                    campaign.getEndDate(),
                    campaign.getEmergency(),
                    campaign.getBloodTypes(),
                    campaign.getDistrict().getId(),
                    campaign.getAddressDetails(),
                    campaign.getOrganization().getName()
            );
            listCampaignsInfo.add(campaignInfo);
        }
        return new Response(200, true, listCampaignsInfo);
    }


    public Response getAllNumberOfRegistration(Integer campaignId) {
        List<DonateRegistration> list = donateRegistrationRepository.findAllRegistration(campaignId,
                DonateRegistrationStatus.CANCELLED);
        return new Response(200, true, list.size());
    }

    public Response getNumberOfRegistrationPerDay(Integer campaignId, NumberOfRegistrationRequest request) {
        List<DonateRegistration> list = new ArrayList<>();
        if (request.getPeriod() == null) {
            list = donateRegistrationRepository.findAllRegistrationAllDay(
                    campaignId,
                    DonateRegistrationStatus.CANCELLED,
                    request.getRegisteredDate()
            );
        }
        else {
            list = donateRegistrationRepository.findAllRegistrationByPeriod(
                    campaignId,
                    DonateRegistrationStatus.CANCELLED,
                    request.getRegisteredDate(),
                    request.getPeriod()
            );
        }
        return new Response(200, true, list.size());
    }

    public Response getAllParticipatedDonor(Integer campaignId) {
        List<DonateRegistration> listOfRegistration = donateRegistrationRepository.findAllRegistration(
                campaignId,
                DonateRegistrationStatus.CANCELLED
        );
        List<ParticipatedDonorResponse> listOfParticipatedDonor = new ArrayList<>();
        for (DonateRegistration registration : listOfRegistration) {
            listOfParticipatedDonor.add(new ParticipatedDonorResponse(
                    registration.getDonor().getName(),
                    registration.getDonor().getIdentityNum(),
                    registration.getDonor().getSex(),
                    registration.getDonor().getBloodType(),
                    registration.getDonor().getAnamnesis(),
                    new DonateRegistrationResponse(registration)
            ));
        }
        return new Response(200, true, listOfParticipatedDonor);
    }

    public Response getAllParticipatedDonorPerDay(Integer campaignId, NumberOfRegistrationRequest request) {
        List<DonateRegistration> listOfRegistration = new ArrayList<>();
        if (request.getPeriod() == null) {
            listOfRegistration = donateRegistrationRepository.findAllRegistrationAllDay(
                    campaignId,
                    DonateRegistrationStatus.CANCELLED,
                    request.getRegisteredDate()
            );
        }
        else {
            listOfRegistration = donateRegistrationRepository.findAllRegistrationByPeriod(
                    campaignId,
                    DonateRegistrationStatus.CANCELLED,
                    request.getRegisteredDate(),
                    request.getPeriod()
            );
        }
        List<ParticipatedDonorResponse> listOfParticipatedDonor = new ArrayList<>();
        for (DonateRegistration registration : listOfRegistration) {
            listOfParticipatedDonor.add(new ParticipatedDonorResponse(
                    registration.getDonor().getName(),
                    registration.getDonor().getIdentityNum(),
                    registration.getDonor().getSex(),
                    registration.getDonor().getBloodType(),
                    registration.getDonor().getAnamnesis(),
                    new DonateRegistrationResponse(registration)
            ));
        }
        return new Response(200, true, listOfParticipatedDonor);
    }

    public Response getTotalAmountOfBlood(Integer campaignId) {
        Integer totalAmount = donateRecordRepository.sumOfAmountByCampaignId(campaignId);
        return new Response(200, true, totalAmount == null ? 0 : totalAmount);
    }


    @Transactional
    public Response updateMedicalDocument(User user, DonateRecordRequest request) {
        Optional<Campaign> isExistCampaign = campaignRepository.findById(request.getCampaignId());
        if (isExistCampaign.isEmpty())
            return new Response(400, false, "Campaign Id not found");
        Optional<Donor> isExistDonor = donorRepository.findById((request.getDonorId()));
        if (isExistDonor.isEmpty())
            return new Response(400, false, "Donor Id not found");
        Optional<DonateRegistration> isExistDonateRegistration = donateRegistrationRepository.findByCampaignIdAndDonorId(
                request.getCampaignId(),
                request.getDonorId(),
                DonateRegistrationStatus.CANCELLED
        );
        if (isExistDonateRegistration.isEmpty())
            return new Response(400, false, "Donor haven't register for a campaign yet");
        if (!user.getId().equals(isExistCampaign.get().getOrganization().getUserId()))
            return new Response(403, false, "You have no right to update medical document of other org's campaign");

        Campaign campaign = isExistCampaign.get();
        Donor donor = isExistDonor.get();
        DonateRegistration donateRegistration = isExistDonateRegistration.get();
        if (donateRegistration.getStatus().equals(DonateRegistrationStatus.NOT_CHECKED_IN)) {
            donateRegistration.setStatus(DonateRegistrationStatus.CHECKED_IN);
            donor.setBloodType(request.getBloodType());
            DonateRecord donateRecord = DonateRecord.builder()
                    .id(new DonateRecordKey(
                            null,
                            null,
                            request.getRegisteredDate()
                    ))
                    .campaign(campaign)
                    .donor(donor)
                    .details(request.getDetails())
                    .status(request.getStatus())
                    .bloodType(request.getBloodType())
                    .amount(request.getAmount())
                    .weight(request.getWeight())
                    .build();
            donateRecordRepository.save(donateRecord);
        }
        else {
            donor.setBloodType(request.getBloodType());
            DonateRecord donateRecord = donateRecordRepository.findByCampaignIdAndDonorId(
                    request.getCampaignId(),
                    request.getDonorId()
            ).get();
            donateRecord.setDetails(request.getDetails());
            donateRecord.setStatus(request.getStatus());
            donateRecord.setBloodType(request.getBloodType());
            donateRecord.setAmount(request.getAmount());
            donateRecord.setWeight(request.getWeight());
        }
        return new Response(200, true, "Update medical history successfully");
    }

    public Response getTotalLike(Integer campaignId) {
        Optional<Campaign> isExistCampaign = campaignRepository.findById(campaignId);
        if (isExistCampaign.isEmpty())
            return new Response(400, false, "ID not found");
        Integer totalLike = likeRecordRepository.countTotalLikeByCampaignId(campaignId);
        return new Response(200, true, totalLike == null ? 0 : totalLike);
    }
}
