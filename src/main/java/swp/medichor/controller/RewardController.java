package swp.medichor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import swp.medichor.model.response.Response;
import swp.medichor.service.RewardService;

@RestController
@RequestMapping("/v1/rewards")
public class RewardController {
    
    @Autowired private RewardService rewardService;
    
    @GetMapping
    public Response getAll() {
        return new Response(200, true, rewardService.getAll());
    }
}
