package swp.medichor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import swp.medichor.model.request.CreateCampaignRequest;
import swp.medichor.model.response.Response;
import swp.medichor.service.CampaignService;

@RestController
@RequestMapping("/v1/campaign")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @PostMapping("/create/{organizationId}")
    public Response createCampaign(@PathVariable("organizationId") Integer organizationId,
                                @RequestBody CreateCampaignRequest request) {
        return campaignService.createCampaign(organizationId, request);
    }

    @PutMapping("/update/{campaignId}")
    public Response updateCampaign(@PathVariable("campaignId") Integer campaignId,
                                   @RequestBody CreateCampaignRequest request) {
        return campaignService.updateCampaign(campaignId, request);
    }

    @GetMapping("/readOne/{campaignId}")
    public Response readOneCampaign(@PathVariable("campaignId") Integer campaignId) {
        return campaignService.readOneCampaign(campaignId);
    }

    @DeleteMapping("/delete/{campaignId}")
    public Response deleteCampaign(@PathVariable("campaignId") Integer campaignId) {
        return campaignService.deleteCampaign(campaignId);
    }

    @PutMapping("/close/{campaignId}")
    public Response closeCampaign(@PathVariable("campaignId") Integer campaignId) {
        return campaignService.closeCampaign(campaignId);
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
    @GetMapping("/getAllActive/{organizationId}")
    public Response getAllActiveCampaigns(@PathVariable("organizationId") Integer organizationId) {
        return campaignService.getAllActiveCampaignsByOrganizationId(organizationId);
    }

    @GetMapping("/getAll/{organizationId}")
    public Response getAllCampaigns(@PathVariable("organizationId") Integer organizationId) {
        return campaignService.getAllCampaignsByOrganizationId(organizationId);
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
}
