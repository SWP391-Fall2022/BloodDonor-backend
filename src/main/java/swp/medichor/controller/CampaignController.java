package swp.medichor.controller;

import java.sql.Date;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
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

    @Secured("ORGANIZATION")
    @PostMapping("/create")
    public Response createCampaign(@RequestAttribute User user,
            @RequestBody CreateCampaignRequest request) {
        return campaignService.createCampaign(user.getOrganization(), request);
    }

    @Secured("ORGANIZATION")
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

    @Secured({"ADMIN", "ORGANIZATION"})
    @DeleteMapping("/delete/{campaignId}")
    public Response deleteCampaign(@RequestAttribute User user,
            @PathVariable("campaignId") Integer campaignId) {
        return campaignService.deleteCampaign(user, campaignId);
    }

    @Secured("ORGANIZATION")
    @PutMapping("/close/{campaignId}")
    public Response closeCampaign(@RequestAttribute User user,
            @PathVariable("campaignId") Integer campaignId) {
        return campaignService.closeCampaign(user, campaignId);
    }

    @Secured("ORGANIZATION")
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
    @Secured("ORGANIZATION")
    @GetMapping("/getAllActiveByOrganization")
    public Response getAllActiveCampaigns(@RequestAttribute User user) {
        return campaignService.getAllActiveCampaignsByOrganizationId(user.getOrganization());
    }

    @Secured("ORGANIZATION")
    @GetMapping("/active")
    public Response getActiveInDayRange(@RequestAttribute User user,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        if (user.getOrganization() != null) {
            return new Response(200, true, campaignService.getAllByOrgIdAndDayRange(user.getId(), from, to));
        } else {
            return new Response(403, false, "Người dùng hiện tại không phải là tổ chức");
        }
    }

    @Secured("ORGANIZATION")
    @GetMapping("/getAllByOrganization")
    public Response getAllCampaigns(@RequestAttribute User user) {
        return campaignService.getAllCampaignsByOrganizationId(user.getOrganization());
    }

    @GetMapping("/getNumberOfRegistration/{campaignId}")
    public Response getAllNumberOfRegistration(@PathVariable("campaignId") Integer campaignId) {
        return campaignService.getAllNumberOfRegistration(campaignId);
    }

    @PostMapping("/getNumberOfRegistrationPerDay/{campaignId}")
    public Response getNumberOfRegistrationPerDay(@PathVariable("campaignId") Integer campaignId,
            @RequestBody NumberOfRegistrationRequest request) {
        return campaignService.getNumberOfRegistrationPerDay(campaignId, request);
    }

    @Secured("ORGANIZATION")
    @PostMapping("/getParticipatedDonor/{campaignId}")
    public Response getAllParticipatedDonor(@PathVariable("campaignId") Integer campaignId) {
        return campaignService.getAllParticipatedDonor(campaignId);
    }

    @Secured("ORGANIZATION")
    @PostMapping("/getParticipatedDonorPerDay/{campaignId}")
    public Response getAllParticipatedDonorPerDay(@PathVariable("campaignId") Integer campaignId,
            @RequestBody NumberOfRegistrationRequest request) {
        return campaignService.getAllParticipatedDonorPerDay(campaignId, request);
    }

    @GetMapping("/getTotalBloodAmount/{campaignId}")
    public Response getTotalAmountOfBlood(@PathVariable Integer campaignId) {
        return campaignService.getTotalAmountOfBlood(campaignId);
    }

    @Secured("ORGANIZATION")
    @PostMapping("/updateMedicalDocument")
    public Response updateMedicalDocument(@RequestAttribute User user,
            @RequestBody DonateRecordRequest request) {
        return campaignService.updateMedicalDocument(user, request);
    }

    @Secured("ORGANIZATION")
    @GetMapping("/medicalDocument/getAll/{campaignId}")
    public Response getAllMedicalDocuments(@RequestAttribute User user,
                                           @PathVariable Integer campaignId) {
        return campaignService.getAllMedicalDocuments(user, campaignId);
    }

    @Secured({"DONOR", "ORGANIZATION"})
    @PostMapping("medicalDocument/getByDonor")
    public Response getMedicalDocumentByDonor(@RequestAttribute User user,
            @RequestBody GetDonateRecordRequest request) {
        return campaignService.getMedicalDocumentByDonor(user, request);
    }



    @GetMapping("/totalLike/{campaignId}")
    public Response getTotalLike(@PathVariable Integer campaignId) {
        return new Response(200, true, campaignService.getTotalLike(campaignId));

    }

    @GetMapping("/top5Provinces")
    public Response getTop5Provinces(@RequestParam Date from, @RequestParam Date to) {
        return new Response(200, true, campaignService.getTop5Provinces(from, to));
    }

    @GetMapping("/top5Orgs")
    public Response getTop5Orgs(@RequestParam Date from, @RequestParam Date to) {
        return new Response(200, true, campaignService.getTop5Orgs(from, to));
    }
}
