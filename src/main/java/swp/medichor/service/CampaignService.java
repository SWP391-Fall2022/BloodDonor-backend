package swp.medichor.service;

import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.medichor.enums.Approve;
import swp.medichor.enums.DonateRegistrationStatus;
import swp.medichor.enums.Role;
import swp.medichor.model.*;
import swp.medichor.model.compositekey.DonateRecordKey;
import swp.medichor.model.request.CreateCampaignRequest;
import swp.medichor.model.request.DonateRecordRequest;
import swp.medichor.model.request.GetDonateRecordRequest;
import swp.medichor.model.request.NumberOfRegistrationRequest;
import swp.medichor.model.response.*;
import swp.medichor.repository.*;
import swp.medichor.utils.EmailPlatform;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private final String FROM = "medichorvn@gmail.com";
    private final String SUBJECT = "PLEASE JOIN IN THE BLOOD DONATION CAMPAIGN IF POSSIBLE";
    private final String MEDICAL_DOCUMENT_SUBJECT = "HAVE A LOOK AT YOUR MEDICAL DOCUMENT";

    public Response createCampaign(Organization organization, CreateCampaignRequest request) {
        if (!organization.getUser().getStatus() || !organization.getUser().getEnabled()
                || organization.getApprove().equals(Approve.PENDING) || organization.getApprove().equals(Approve.REJECTED)) {
            return new Response(403, false, "The account is disabled or unverified");
        }
        if (!campaignRepository.findByOrganizationIdAndCampaignName(organization.getUserId(), request.getName()).isEmpty())
            return new Response(400, false, "Campaign can not have duplicate name.");

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
        }

        List<LocalDate> onSiteDatesList = new ArrayList<>();
        String onSiteDates = null;
        if (!request.isEmergency()) {
            if (request.getOnSiteDates() != null) {
                for (LocalDate date : request.getOnSiteDates()) {
                    if (date.compareTo(request.getStartDate()) < 0 || date.compareTo(request.getEndDate()) > 0)
                        return new Response(400, false, "On-site dates must be between start date and end date.");
                }
                request.getOnSiteDates().sort((d1, d2) -> {
                    return Integer.compare(d1.compareTo(d2), 0);
                });
                onSiteDatesList = request.getOnSiteDates();
            }
            //weekRepetition = true
            //daysOfWeek = ["MONDAY", "FRIDAY"]
            else if (request.isWeekRepetition()) {
                for (LocalDate date = request.getStartDate(); date.compareTo(request.getEndDate()) <= 0; date =
                        date.plusDays(1)) {
                    if (request.getDaysOfWeek().contains(date.getDayOfWeek()))
                        onSiteDatesList.add(date);
                }
            }
            //monthRepetition = true
            //daysOfMonth = [1, 15, 20]
            //or
            //weekNumber = 4
            //daysOfWeek = ["MONDAY", "FRIDAY"]
            else if (request.isMonthRepetition()) {
                if (request.getDaysOfMonth() != null) {
                    for (LocalDate date = request.getStartDate(); date.compareTo(request.getEndDate()) <= 0; date =
                            date.plusDays(1)) {
                        if (request.getDaysOfMonth().contains(date.getDayOfMonth()))
                            onSiteDatesList.add(date);
                    }
                }
                else if (request.getDaysOfWeek() != null && request.getWeekNumber() != null) {
                    WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY, 1);
                    TemporalField weekOfMonth = weekFields.weekOfMonth();
                    for (LocalDate date = request.getStartDate(); date.compareTo(request.getEndDate()) <= 0; date =
                            date.plusDays(1)) {
                        if (date.get(weekOfMonth) == request.getWeekNumber() && request.getDaysOfWeek().contains(date.getDayOfWeek()))
                            onSiteDatesList.add(date);
                    }
                }
            }
        }


        if (onSiteDatesList.size() > 0)
            onSiteDates = onSiteDatesList.stream().map(LocalDate::toString).collect(Collectors.joining(" "));

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
                .onSiteDates(onSiteDates)
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

        CampaignResponse campaignInfo = new CampaignResponse(campaign);
        return new Response(200, true, campaignInfo);
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
        }

        List<LocalDate> onSiteDatesList = new ArrayList<>();
        String onSiteDates = null;
        if (!request.isEmergency()) {
            if (request.getOnSiteDates() != null) {
                for (LocalDate date : request.getOnSiteDates()) {
                    if (date.compareTo(request.getStartDate()) < 0 || date.compareTo(request.getEndDate()) > 0)
                        return new Response(400, false, "On-site dates must be between start date and end date.");
                }
                request.getOnSiteDates().sort((d1, d2) -> {
                    return Integer.compare(d1.compareTo(d2), 0);
                });
                onSiteDatesList = request.getOnSiteDates();
            }
            //weekRepetition = true
            //daysOfWeek = ["MONDAY", "FRIDAY"]
            else if (request.isWeekRepetition()) {
                for (LocalDate date = request.getStartDate(); date.compareTo(request.getEndDate()) <= 0; date =
                        date.plusDays(1)) {
                    if (request.getDaysOfWeek().contains(date.getDayOfWeek()))
                        onSiteDatesList.add(date);
                }
            }
            //monthRepetition = true
            //daysOfMonth = [1, 15, 20]
            //or
            //weekNumber = 4
            //daysOfWeek = ["MONDAY", "FRIDAY"]
            else if (request.isMonthRepetition()) {
                if (request.getDaysOfMonth() != null) {
                    for (LocalDate date = request.getStartDate(); date.compareTo(request.getEndDate()) <= 0; date =
                            date.plusDays(1)) {
                        if (request.getDaysOfMonth().contains(date.getDayOfMonth()))
                            onSiteDatesList.add(date);
                    }
                }
                else if (request.getDaysOfWeek() != null && request.getWeekNumber() != null) {
                    WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY, 1);
                    TemporalField weekOfMonth = weekFields.weekOfMonth();
                    for (LocalDate date = request.getStartDate(); date.compareTo(request.getEndDate()) <= 0; date =
                            date.plusDays(1)) {
                        if (date.get(weekOfMonth) == request.getWeekNumber() && request.getDaysOfWeek().contains(date.getDayOfWeek()))
                            onSiteDatesList.add(date);
                    }
                }
            }
        }


        if (onSiteDatesList.size() > 0)
            onSiteDates = onSiteDatesList.stream().map(LocalDate::toString).collect(Collectors.joining(" "));

        campaign.setName(request.getName());
        campaign.setImages(request.getImages());
        campaign.setDescription(request.getDescription());
        campaign.setStartDate(request.getStartDate());
        campaign.setEndDate(request.getEndDate());
        campaign.setEmergency(request.isEmergency());
        campaign.setBloodTypes(request.getBloodTypes());
        campaign.setDistrict(District.builder().id(request.getDistrictId()).build());
        campaign.setAddressDetails(request.getAddressDetails());
        campaign.setOnSiteDates(onSiteDates);

        List<DonateRegistration> listOfOutdatedRegistration = new ArrayList<>();
        if (request.isEmergency()) {
            listOfOutdatedRegistration = donateRegistrationRepository.findUrgentByCampaignIdAndOutDate(
                    campaignId,
                    request.getStartDate(),
                    DonateRegistrationStatus.NOT_CHECKED_IN
            );
        }
        else {
            if (onSiteDatesList.size() == 0) {
                listOfOutdatedRegistration = donateRegistrationRepository.findNormalByCampaignIdAndOutDate(
                        campaignId,
                        request.getStartDate(),
                        request.getEndDate(),
                        DonateRegistrationStatus.NOT_CHECKED_IN
                );
            }
            else {
                listOfOutdatedRegistration = donateRegistrationRepository.findNormalByCampaignIdAndOutOnsiteDate(
                        campaignId,
                        onSiteDatesList,
                        DonateRegistrationStatus.NOT_CHECKED_IN
                );
            }

        }
        for (DonateRegistration outDatedRegistration : listOfOutdatedRegistration) {
            outDatedRegistration.setStatus(DonateRegistrationStatus.CANCELLED);
            emailService.send(FROM, outDatedRegistration.getDonor().getUser().getEmail(), SUBJECT,
                    EmailPlatform.buildChangeCampaignEmail(
                    outDatedRegistration.getDonor().getName(),
                    campaign.getName()
            ));
        }

        CampaignResponse campaignInfo = new CampaignResponse(campaign);
        return new Response(200, true, campaignInfo);
    }

    public Response readOneCampaign(Integer campaignId) {
        Optional<Campaign> isExistCampaign = campaignRepository.findById(campaignId);
        if (isExistCampaign.isEmpty())
            return new Response(400, false, "ID not found");
        Campaign campaign = isExistCampaign.get();
        CampaignResponse campaignInfo = new CampaignResponse(campaign);
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
                    campaign.getStartDate(),
                    LocalDate.now().minusDays(1),
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
        // If campaign has not started
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
                    campaign.getStartDate(),
                    campaign.getEndDate(),
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

    @Transactional
    public Response cancelOutdatedDonateRegistration(User user, Integer campaignId) {
        Optional<Campaign> isExistCampaign = campaignRepository.findById(campaignId);
        if (isExistCampaign.isEmpty())
            return new Response(400, false, "ID not found");
        Campaign campaign = isExistCampaign.get();
        if (!campaign.getOrganization().getUserId().equals(user.getId())) {
            return new Response(403, false, "You have no right to edit other org's campaign");
        }
        List<DonateRegistration> donateRegistrationList = donateRegistrationRepository.findUrgentByCampaignIdAndOutDate(
                campaignId,
                LocalDate.now(),
                DonateRegistrationStatus.NOT_CHECKED_IN
        );
        for (DonateRegistration donateRegistration : donateRegistrationList) {
            donateRegistration.setStatus(DonateRegistrationStatus.CANCELLED);
        }
        return new Response(200, true, "Cancel outdated donate registrations successfully");
    }

    public Response getAllActiveCampaigns() {
        List<Campaign> listActiveCampaigns = new ArrayList<>();
        List<CampaignResponse> listActiveCampaignsInfo = new ArrayList<>();
        listActiveCampaigns = campaignRepository.findAllActiveCampaigns(LocalDate.now());
        for (Campaign campaign : listActiveCampaigns) {
            CampaignResponse campaignInfo = new CampaignResponse(campaign);
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
            CampaignResponse campaignInfo = new CampaignResponse(campaign);
            listActiveCampaignsInfo.add(campaignInfo);
        }
        return new Response(200, true, listActiveCampaignsInfo);
    }
    
    public List<CampaignResponse> getAllByOrgIdAndDayRange(int orgId, LocalDate from, LocalDate to){
        return campaignRepository.findAllActiveCampaignsByOrganizationId(orgId, LocalDate.now())
                .stream()
                .filter(c -> c.getEndDate() == null
                        || (!c.getEndDate().isBefore(from) && !c.getEndDate().isAfter(to)))
                .map(c -> new CampaignResponse(c))
                .collect(Collectors.toList());
    }

    public Response getAllCampaigns() {
        List<Campaign> listCampaigns = campaignRepository.findAllCampaigns();
        List<CampaignResponse> listCampaignsInfo = new ArrayList<>();
        for (Campaign campaign : listCampaigns) {
            CampaignResponse campaignInfo = new CampaignResponse(campaign);
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
            CampaignResponse campaignInfo = new CampaignResponse(campaign);
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
                DonateRegistrationStatus.CANCELLED,
                request.getRegisteredDate().toLocalDate()
        );
        if (isExistDonateRegistration.isEmpty())
            return new Response(400, false, "Donor haven't register for a campaign yet");
        if (!user.getId().equals(isExistCampaign.get().getOrganization().getUserId()))
            return new Response(403, false, "You have no right to update medical document of other org's campaign");

        Campaign campaign = isExistCampaign.get();
        Donor donor = isExistDonor.get();
        DonateRegistration donateRegistration = isExistDonateRegistration.get();
        DonateRecord donateRecord;
        if (donateRegistration.getStatus().equals(DonateRegistrationStatus.NOT_CHECKED_IN)) {
            donateRegistration.setStatus(DonateRegistrationStatus.CHECKED_IN);
            donor.setBloodType(request.getBloodType());
            donateRecord = DonateRecord.builder()
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
            donateRecord = donateRecordRepository.findByCampaignIdAndDonorId(
                    request.getCampaignId(),
                    request.getDonorId(),
                    request.getRegisteredDate()
            ).get();
            donateRecord.setDetails(request.getDetails());
            donateRecord.setStatus(request.getStatus());
            donateRecord.setBloodType(request.getBloodType());
            donateRecord.setAmount(request.getAmount());
            donateRecord.setWeight(request.getWeight());
        }

        //send mail
        emailService.send(FROM, donor.getUser().getEmail(), MEDICAL_DOCUMENT_SUBJECT, EmailPlatform.buildMedicalDocumentEmail(
                donateRecord
        ));
        DonateRecordResponse donateRecordResponse = new DonateRecordResponse(donateRecord);
        return new Response(200, true, donateRecordResponse);
    }

    public Response getAllMedicalDocuments(Integer campaignId) {
        Optional<Campaign> isExistCampaign = campaignRepository.findById(campaignId);
        if (isExistCampaign.isEmpty())
            return new Response(400, false, "Campaign Id not found");
        List<DonateRecord> listOfDonateRecord = donateRecordRepository.findByCampaignId(campaignId);
        List<DonateRecordResponse> response = new ArrayList<>();
        for (DonateRecord donateRecord : listOfDonateRecord) {
            response.add(new DonateRecordResponse(donateRecord));
        }
        return new Response(200, true, response);
    }

    public Response getMedicalDocumentByDonor(Integer userID, GetDonateRecordRequest request) {
        Optional<Campaign> isExistCampaign = campaignRepository.findById(request.getCampaignId());
        if (isExistCampaign.isEmpty())
            return new Response(400, false, "Campaign Id not found");
        Optional<DonateRecord> isExistDonateRecord = donateRecordRepository.findByCampaignIdAndDonorId(
                request.getCampaignId(),
                userID,
                request.getRegisteredDate()
        );
        if (isExistDonateRecord.isEmpty())
            return new Response(400, false, "Donor haven't donated blood on this date yet");
        DonateRecordResponse donateRecordResponse = new DonateRecordResponse(isExistDonateRecord.get());
        return new Response(200, true, donateRecordResponse);
    }

    public Response getTotalLike(Integer campaignId) {
        Optional<Campaign> isExistCampaign = campaignRepository.findById(campaignId);
        if (isExistCampaign.isEmpty())
            return new Response(400, false, "ID not found");
        Integer totalLike = likeRecordRepository.countTotalLikeByCampaignId(campaignId);
        return new Response(200, true, totalLike == null ? 0 : totalLike);
    }

    public List<Map<String, Object>> getTop5Provinces(Date from, Date to) {
        return campaignRepository.findTop5Provinces(from, to);
    }

    public List<Map<String, Object>> getTop5Orgs(Date from, Date to) {
        return campaignRepository.findTop5Orgs(from, to);
    }
}
