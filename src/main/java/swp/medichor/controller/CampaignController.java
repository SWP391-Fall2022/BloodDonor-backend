package swp.medichor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import swp.medichor.model.User;
import swp.medichor.model.request.CreateCampaignRequest;
import swp.medichor.model.request.DonateRecordRequest;
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

    @GetMapping("/getAllByOrganization")
    public Response getAllCampaigns(@RequestAttribute User user) {
        return campaignService.getAllCampaignsByOrganizationId(user.getOrganization());
    }

    @GetMapping("/getNumberOfRegistration/allDay/{campaignId}")
    public Response getNumberOfRegistrationAllDay(@PathVariable("campaignId") Integer campaignId) {
        return campaignService.getNumberOfRegistrationAllDay(campaignId);
    }

    @GetMapping("/getNumberOfRegistration/morning/{campaignId}")
    public Response getNumberOfRegistrationMorning(@PathVariable("campaignId") Integer campaignId) {
        return campaignService.getNumberOfRegistrationMorning(campaignId);
    }

    @GetMapping("/getNumberOfRegistration/afternoon/{campaignId}")
    public Response getNumberOfRegistrationAfternoon(@PathVariable("campaignId") Integer campaignId) {
        return campaignService.getNumberOfRegistrationAfternoon(campaignId);
    }

    @GetMapping("/getParticipatedDonor/{campaignId}")
    public Response getAllParticipatedDonor(@PathVariable("campaignId") Integer campaignId) {
        return campaignService.getAllParticipatedDonor(campaignId);
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

    @GetMapping("/totalLike/{campaignId}")
    public Response getTotalLike(@PathVariable Integer campaignId) {
        return campaignService.getTotalLike(campaignId);
    }
}
