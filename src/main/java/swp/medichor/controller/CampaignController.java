package swp.medichor.controller;

import java.sql.Date;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import swp.medichor.model.User;
import swp.medichor.model.request.CreateCampaignRequest;
import swp.medichor.model.request.DonateRecordRequest;
import swp.medichor.model.request.GetDonateRecordRequest;
import swp.medichor.model.request.NumberOfRegistrationRequest;
import swp.medichor.model.response.Response;
import swp.medichor.service.CampaignService;

@RestController
@RequestMapping("/v1/campaign")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @PostMapping("/create")
    public Response createCampaign(@RequestAttribute User user,
            @RequestBody CreateCampaignRequest request) {
        return campaignService.createCampaign(user.getOrganization(), request);
    }

    @PutMapping("/update/{campaignId}")
    public Response updateCampaign(@RequestAttribute User user,
            @PathVariable("campaignId") Integer campaignId,
            @RequestBody CreateCampaignRequest request) {
        return campaignService.updateCampaign(user, campaignId, request);
    }

    @GetMapping("/readOne/{campaignId}")
    public Response readOneCampaign(@PathVariable("campaignId") Integer campaignId) {
        return campaignService.readOneCampaign(campaignId);
    }

    @DeleteMapping("/delete/{campaignId}")
    public Response deleteCampaign(@RequestAttribute User user,
            @PathVariable("campaignId") Integer campaignId) {
        return campaignService.deleteCampaign(user, campaignId);
    }

    @PutMapping("/close/{campaignId}")
    public Response closeCampaign(@RequestAttribute User user,
            @PathVariable("campaignId") Integer campaignId) {
        return campaignService.closeCampaign(user, campaignId);
    }

    @PutMapping("/cancel-outdated-registration/{campaignId}")
    public Response cancelOutdatedDonateRegistration(@RequestAttribute User user,
            @PathVariable Integer campaignId) {
        return campaignService.cancelOutdatedDonateRegistration(user, campaignId);
    }

    //Get all active campaigns of all organizations
    @GetMapping("/getAllActive")
    public Response getAllActiveCampaigns() {
        return campaignService.getAllActiveCampaigns();
    }

    @GetMapping("/getAll")
    public Response getAllCampaigns() {
        return campaignService.getAllCampaigns();
    }

    //Get all active campaigns of a particular organizations
    @GetMapping("/getAllActiveByOrganization")
    public Response getAllActiveCampaigns(@RequestAttribute User user) {
        return campaignService.getAllActiveCampaignsByOrganizationId(user.getOrganization());
    }

    @GetMapping("/active")
    public Response getActiveInDayRange(@RequestAttribute User user,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        if (user.getOrganization() != null) {
            return new Response(200, true, campaignService.getAllByOrgIdAndDayRange(user.getId(), from, to));
        } else {
            return new Response(403, false, "Current user is not an orrganization");
        }
    }

    @GetMapping("/getAllByOrganization")
    public Response getAllCampaigns(@RequestAttribute User user) {
        return campaignService.getAllCampaignsByOrganizationId(user.getOrganization());
    }

    @GetMapping("/getNumberOfRegistration/{campaignId}")
    public Response getAllNumberOfRegistration(@PathVariable("campaignId") Integer campaignId) {
        return campaignService.getAllNumberOfRegistration(campaignId);
    }

    @GetMapping("/getNumberOfRegistrationPerDay/{campaignId}")
    public Response getNumberOfRegistrationPerDay(@PathVariable("campaignId") Integer campaignId,
            @RequestBody NumberOfRegistrationRequest request) {
        return campaignService.getNumberOfRegistrationPerDay(campaignId, request);
    }

    @GetMapping("/getParticipatedDonor/{campaignId}")
    public Response getAllParticipatedDonor(@PathVariable("campaignId") Integer campaignId) {
        return campaignService.getAllParticipatedDonor(campaignId);
    }

    @GetMapping("/getParticipatedDonorPerDay/{campaignId}")
    public Response getAllParticipatedDonorPerDay(@PathVariable("campaignId") Integer campaignId,
            @RequestBody NumberOfRegistrationRequest request) {
        return campaignService.getAllParticipatedDonorPerDay(campaignId, request);
    }

    @GetMapping("/getTotalBloodAmount/{campaignId}")
    public Response getTotalAmountOfBlood(@PathVariable Integer campaignId) {
        return campaignService.getTotalAmountOfBlood(campaignId);
    }

    @PostMapping("/updateMedicalDocument")
    public Response updateMedicalDocument(@RequestAttribute User user,
            @RequestBody DonateRecordRequest request) {
        return campaignService.updateMedicalDocument(user, request);
    }

    @GetMapping("/medicalDocument/getAll/{campaignId}")
    public Response getAllMedicalDocuments(@PathVariable Integer campaignId) {
        return campaignService.getAllMedicalDocuments(campaignId);
    }

    @GetMapping("medicalDocument/getByDonor")
    public Response getMedicalDocumentByDonor(@RequestAttribute User user,
            @RequestBody GetDonateRecordRequest request) {
        return campaignService.getMedicalDocumentByDonor(user.getDonor().getUserId(), request);
    }

    @GetMapping("/totalLike/{campaignId}")
    public Response getTotalLike(@PathVariable Integer campaignId) {
        return campaignService.getTotalLike(campaignId);
    }

    @GetMapping("/top5Provinces")
    public Response getTop5Provinces(@RequestParam Date from, @RequestParam Date to) {
        return new Response(200, true, campaignService.getTop5Provinces(from, to));
    }
}
