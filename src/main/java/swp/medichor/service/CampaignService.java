package swp.medichor.service;

import io.opencensus.trace.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.medichor.enums.Approve;
import swp.medichor.enums.DonateRegistrationStatus;
import swp.medichor.enums.Period;
import swp.medichor.model.*;
import swp.medichor.model.request.CreateCampaignRequest;
import swp.medichor.model.response.CampaignResponse;
import swp.medichor.model.response.DonateRegistrationResponse;
import swp.medichor.model.response.ParticipatedDonorReponse;
import swp.medichor.model.response.Response;
import swp.medichor.repository.CampaignRepository;
import swp.medichor.repository.DonateRegistrationRepository;
import swp.medichor.repository.DonorRepository;
import swp.medichor.repository.OrganizationRepository;
import swp.medichor.utils.EmailPlatform;

import javax.transaction.Transactional;
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
    private UserService userService;
    @Autowired
    private EmailService emailService;
    private final String FROM = "nvtien1602.forwork@gmail.com";
    private final String SUBJECT = "PLEASE JOIN IN THE BLOOD DONATION CAMPAIGN IF POSSIBLE";

    public Response createCampaign(Integer organizationId, CreateCampaignRequest request) {
        Optional<Organization> isExistOrganization = organizationRepository.findById(organizationId);
        if (isExistOrganization.isEmpty())
            return new Response(400, false, "ID not found");
        Organization organization = isExistOrganization.get();
        if (!organization.getUser().getStatus() || !organization.getUser().getEnabled()
                || organization.getApprove().equals(Approve.PENDING) || organization.getApprove().equals(Approve.REJECTED)) {
            return new Response(403, false, "The account is disabled or unverified");
        }

        if (request.getStartDate().isAfter(request.getEndDate()))
            return new Response(400, false, "Start date can not be after end date");
        if (request.getEndDate().isBefore(LocalDate.now()))
            return new Response(400, false, "End date can not be before today");

        Campaign campaign = Campaign.builder()
                .name(request.getName())
                .images(request.getImages())
                .description(request.getDescription())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .emergency(request.isEmergency())
                .status(true)
                .district(District.builder().id(request.getDistrictId()).build())
                .addressDetails(request.getAddressDetails())
                .organization(Organization.builder().userId(organizationId).build())
                .build();
        campaign = campaignRepository.save(campaign);
        //send email to all donors in the same district
        List<User> listOfDonors = userService.getAllDonorsByDistrictId(request.getDistrictId());

        for (User user : listOfDonors) {
            emailService.send(FROM, user.getEmail(), SUBJECT, EmailPlatform.buildJoinInCampaignEmail(
                    user.getDonor().getName(),
                    campaign.getName()
            ));
        }
        return new Response(200, true, "Create campaign successfully");
    }

    @Transactional
    public Response updateCampaign(Integer campaignId, CreateCampaignRequest request) {
        Optional<Campaign> isExistCampaign = campaignRepository.findById(campaignId);
        if (isExistCampaign.isEmpty())
            return new Response(400, false, "ID not found");
        if (request.getStartDate().isAfter(request.getEndDate()))
            return new Response(400, false, "Start date can not be after end date");
        if (request.getEndDate().isBefore(LocalDate.now()))
            return new Response(400, false, "End date can not be before today");
        Campaign campaign = isExistCampaign.get();
        campaign.setName(request.getName());
        campaign.setImages(request.getImages());
        campaign.setDescription(request.getDescription());
        campaign.setStartDate(request.getStartDate());
        campaign.setEndDate(request.getEndDate());
        campaign.setEmergency(request.isEmergency());
        campaign.setDistrict(District.builder().id(request.getDistrictId()).build());
        campaign.setAddressDetails(request.getAddressDetails());
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
                campaign.getDistrict().getId(),
                campaign.getAddressDetails(),
                campaign.getOrganization().getName()
        );
        return new Response(200, true, campaignInfo);
    }

    @Transactional
    public Response deleteCampaign(Integer campaignId) {
        Optional<Campaign> isExistCampaign = campaignRepository.findById(campaignId);
        if (isExistCampaign.isEmpty())
            return new Response(400, false, "ID not found");
        Campaign campaign = isExistCampaign.get();
        campaign.setStatus(false);
        return new Response(200, true, "Delete successfully");
    }

    @Transactional
    public Response closeCampaign(Integer campaignId) {
        Optional<Campaign> isExistCampaign = campaignRepository.findById(campaignId);
        if (isExistCampaign.isEmpty())
            return new Response(400, false, "ID not found");
        Campaign campaign = isExistCampaign.get();
        if (campaign.getStartDate().compareTo(LocalDate.now()) >= 0)
            campaign.setEndDate(campaign.getStartDate());
        else {
            campaign.setEndDate(LocalDate.now().minusDays(1));
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
                    campaign.getDistrict().getId(),
                    campaign.getAddressDetails(),
                    campaign.getOrganization().getName()
            );
            listActiveCampaignsInfo.add(campaignInfo);
        }
        return new Response(200, true, listActiveCampaignsInfo);
    }

    public Response getAllActiveCampaignsByOrganizationId(Integer organizationId) {
        Optional<Organization> isExistOrganization = organizationRepository.findById(organizationId);
        if (isExistOrganization.isEmpty())
            return new Response(400, false, "ID not found");
        Organization organization = isExistOrganization.get();
        if (!organization.getUser().getStatus() || !organization.getUser().getEnabled()
                || organization.getApprove().equals(Approve.PENDING) || organization.getApprove().equals(Approve.REJECTED)) {
            return new Response(403, false, "The account is disabled or unverified");
        }

        List<Campaign> listActiveCampaigns = new ArrayList<>();
        List<CampaignResponse> listActiveCampaignsInfo = new ArrayList<>();
        listActiveCampaigns = campaignRepository.findAllActiveCampaignsByOrganizationId(organizationId,
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
                    campaign.getDistrict().getId(),
                    campaign.getAddressDetails(),
                    campaign.getOrganization().getName()
            );
            listActiveCampaignsInfo.add(campaignInfo);
        }
        return new Response(200, true, listActiveCampaignsInfo);
    }

    public Response getAllCampaigns() {
        List<Campaign> listCampaigns = new ArrayList<>();
        List<CampaignResponse> listCampaignsInfo = new ArrayList<>();
        listCampaigns = campaignRepository.findAllCampaigns();
        for (Campaign campaign : listCampaigns) {
            CampaignResponse campaignInfo = new CampaignResponse(
                    campaign.getId(),
                    campaign.getName(),
                    campaign.getImages(),
                    campaign.getDescription(),
                    campaign.getStartDate(),
                    campaign.getEndDate(),
                    campaign.getEmergency(),
                    campaign.getDistrict().getId(),
                    campaign.getAddressDetails(),
                    campaign.getOrganization().getName()
            );
            listCampaignsInfo.add(campaignInfo);
        }
        return new Response(200, true, listCampaignsInfo);
    }

    public Response getAllCampaignsByOrganizationId(Integer organizationId) {
        Optional<Organization> isExistOrganization = organizationRepository.findById(organizationId);
        if (isExistOrganization.isEmpty())
            return new Response(400, false, "ID not found");
        Organization organization = isExistOrganization.get();
        if (!organization.getUser().getStatus() || !organization.getUser().getEnabled()
                || organization.getApprove().equals(Approve.PENDING) || organization.getApprove().equals(Approve.REJECTED)) {
            return new Response(403, false, "The account is disabled or unverified");
        }

        List<Campaign> listCampaigns = new ArrayList<>();
        List<CampaignResponse> listCampaignsInfo = new ArrayList<>();
        listCampaigns = campaignRepository.findAllCampaignsByOrganizationId(organizationId);
        for (Campaign campaign : listCampaigns) {
            CampaignResponse campaignInfo = new CampaignResponse(
                    campaign.getId(),
                    campaign.getName(),
                    campaign.getImages(),
                    campaign.getDescription(),
                    campaign.getStartDate(),
                    campaign.getEndDate(),
                    campaign.getEmergency(),
                    campaign.getDistrict().getId(),
                    campaign.getAddressDetails(),
                    campaign.getOrganization().getName()
            );
            listCampaignsInfo.add(campaignInfo);
        }
        return new Response(200, true, listCampaignsInfo);
    }


    public Response getNumberOfRegistrationAllDay(Integer campaignId) {
        List<DonateRegistration> list = donateRegistrationRepository.findAllRegistrationAllDay(campaignId,
                DonateRegistrationStatus.CANCELLED);
        return new Response(200, true, list.size());
    }

    public Response getNumberOfRegistrationMorning(Integer campaignId) {
        List<DonateRegistration> list = donateRegistrationRepository.findAllRegistrationByPeriod(campaignId,
                DonateRegistrationStatus.CANCELLED, Period.MORNING);
        return new Response(200, true, list.size());
    }

    public Response getNumberOfRegistrationAfternoon(Integer campaignId) {
        List<DonateRegistration> list = donateRegistrationRepository.findAllRegistrationByPeriod(campaignId,
                DonateRegistrationStatus.CANCELLED, Period.AFTERNOON);
        return new Response(200, true, list.size());
    }

    public Response getAllParticipatedDonor(Integer campaignId) {
        List<DonateRegistration> listOfRegistration = donateRegistrationRepository.findAllRegistrationAllDay(
                campaignId,
                DonateRegistrationStatus.CANCELLED
        );
        List<ParticipatedDonorReponse> listOfParticipatedDonor = new ArrayList<>();
        for (DonateRegistration registration : listOfRegistration) {
            listOfParticipatedDonor.add(new ParticipatedDonorReponse(
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


}
