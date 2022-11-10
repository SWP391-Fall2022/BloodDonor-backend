package swp.medichor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import swp.medichor.model.request.RewardRequest;
import swp.medichor.model.response.Response;
import swp.medichor.model.response.RewardResponse;
import swp.medichor.service.RewardService;

@RestController
@RequestMapping("/v1/rewards")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @GetMapping
    public Response getAll() {
        return new Response(200, true, rewardService.getAll());
    }

    @GetMapping("/{id}")
    public Response getReward(@PathVariable int id) {
        RewardResponse reward = rewardService.getById(id);
        if (reward != null) {
            return new Response(200, true, reward);
        } else {
            return new Response(404, false, "Không tìm thấy mã khuyến mãi");
        }
    }

    @PostMapping
    public Response addReward(@RequestBody RewardRequest request) {
        return new Response(200, true, rewardService.addReward(request));
    }

    @PutMapping("/{id}")
    public Response updateReward(@PathVariable int id, @RequestBody RewardRequest request) {
        try {
            rewardService.updateReward(id, request);
            return new Response(200, true, null);
        } catch (Exception ex) {
            return new Response(400, false, ex.getLocalizedMessage());
        }
    }

    @PutMapping("/{id}/disable")
    public Response disableReward(@PathVariable int id) {
        try {
            rewardService.disableReward(id);
            return new Response(200, true, null);
        } catch (Exception ex) {
            return new Response(400, false, ex.getLocalizedMessage());
        }
    }
}
