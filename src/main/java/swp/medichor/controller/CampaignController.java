package swp.medichor.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import swp.medichor.model.Campaign;
import swp.medichor.model.User;
import swp.medichor.model.request.DonateRegistrationRequest;
import swp.medichor.model.response.Response;
import swp.medichor.service.CampaignService;

@RestController
@RequestMapping("/v1/campaigns")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @PutMapping("/{id}/registered")
    public Response registerDonor(@PathVariable int id, @RequestAttribute User user, @RequestBody DonateRegistrationRequest registration) {
        try {
            if (user.getDonor() != null) {
                campaignService.registerDonor(id, user.getDonor(), registration);
                return new Response(200, true, null);
            }
            return new Response(403, false, "Current user is not a donor");
        } catch (IllegalArgumentException e) {
            return new Response(400, false, e.getLocalizedMessage());
        }
    }
}
