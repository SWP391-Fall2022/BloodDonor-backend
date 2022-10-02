package swp.medichor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import swp.medichor.service.CampaignService;

@RestController
@RequestMapping("/v1/campaigns")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;
}
