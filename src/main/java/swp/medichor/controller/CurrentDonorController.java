package swp.medichor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swp.medichor.model.User;
import swp.medichor.model.request.DonateRegistrationRequest;
import swp.medichor.model.request.UpdateDonorRequest;
import swp.medichor.model.response.DonorResponse;
import swp.medichor.model.response.Response;
import swp.medichor.service.DonorService;
import swp.medichor.service.RewardService;

@RestController
@RequestMapping("/v1/donors/me")
public class CurrentDonorController {

    @Autowired
    private DonorService donorService;
    @Autowired
    private RewardService rewardService;

    @GetMapping
    public Response getProfile(@RequestAttribute User user) {
        if (user != null && user.getDonor() != null) {
            return new Response(200, true, new DonorResponse(user.getDonor()));
        }
        return new Response(404, false, null);
    }

    @PutMapping
    public Response updateProfile(@RequestBody UpdateDonorRequest donor, @RequestAttribute User user) {
        donorService.updateDonor(user.getDonor(), donor);
        return new Response(200, true, null);
    }

    @PutMapping("/registered")
    public Response registerDonor(@RequestAttribute User user, @RequestBody DonateRegistrationRequest registration) {
        try {
            if (user.getDonor() != null) {
                donorService.registerDonor(user.getDonor(), registration);
                return new Response(200, true, null);
            }
            return new Response(403, false, "Current user is not a donor");
        } catch (Exception e) {
            return new Response(400, false, e.getLocalizedMessage());
        }
    }

    @PutMapping("/registered/{registrationId}")
    public Response updateRegistration(@RequestAttribute User user,
            @RequestBody DonateRegistrationRequest registration,
            @PathVariable int registrationId) {
        try {
            if (user.getDonor() != null) {
                donorService.updateDonateRegistration(user.getId(), registrationId, registration);
                return new Response(200, true, null);
            }
            return new Response(403, false, "Current user is not a donor");
        } catch (Exception e) {
            return new Response(400, false, e.getLocalizedMessage());
        }
    }

    @GetMapping("/registered")
    public Response getRegistrations(@RequestAttribute User user) {
        if (user.getDonor() != null) {
            return new Response(200, true, donorService.getAllRegistrations(user.getId()));
        } else {
            return new Response(403, false, "Current user is not a donor");
        }
    }

    @DeleteMapping("/registered/{campaignId}")
    public Response cancelRegistration(@RequestAttribute User user, @PathVariable int campaignId) {
        try {
            if (user.getDonor() != null) {
                donorService.cancelRegistration(user.getId(), campaignId);
                return new Response(200, true, null);
            } else {
                return new Response(403, false, "Current user is not a donor");
            }
        } catch (Exception ex) {
            return new Response(400, false, ex.getLocalizedMessage());
        }
    }

    @GetMapping("/donated")
    public Response getDonations(@RequestAttribute User user) {
        if (user.getDonor() != null) {
            return new Response(200, true, donorService.getAllDonations(user.getId()));
        } else {
            return new Response(403, false, "Current user is not a donor");
        }
    }

    @PostMapping("/campaign-interest/{campaignId}")
    public Response likeCampaign(@RequestAttribute User user,
            @PathVariable Integer campaignId) {
        return donorService.likeCampaign(user, campaignId);
    }

    @GetMapping("/points")
    public Response getPoints(@RequestAttribute User user) {
        try {
            return new Response(200, true, donorService.getPoints(user.getId()));
        } catch (Exception ex) {
            return new Response(400, false, ex.getLocalizedMessage());
        }
    }

    @GetMapping("/rewards")
    public Response getEarnedRewards(@RequestAttribute User user) {
        try {
            return new Response(200, true, rewardService.getEarned(user.getId()));
        } catch (Exception ex) {
            return new Response(400, false, ex.getLocalizedMessage());
        }
    }

    @GetMapping("/rewards/available")
    public Response getAvailableRewards(@RequestAttribute User user) {
        try {
            return new Response(200, true, rewardService.getAllAvailable(user.getId()));
        } catch (Exception ex) {
            return new Response(400, false, ex.getLocalizedMessage());
        }
    }

    @PutMapping("/rewards/{rewardId}")
    public Response claimReward(@RequestAttribute User user, @PathVariable int rewardId) {
        try {
            donorService.claimReward(user.getId(), rewardId);
            return new Response(200, true, null);
        } catch (Exception ex) {
            return new Response(400, false, ex.getLocalizedMessage());
        }
    }
}
