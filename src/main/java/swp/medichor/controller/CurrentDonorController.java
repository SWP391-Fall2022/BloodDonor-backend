package swp.medichor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swp.medichor.model.User;
import swp.medichor.model.request.DonateRegistrationRequest;
import swp.medichor.model.request.UpdateDonorRequest;
import swp.medichor.model.response.DonorResponse;
import swp.medichor.model.response.Response;
import swp.medichor.service.DonorService;

@RestController
@RequestMapping("/v1/donors/me")
public class CurrentDonorController {

    @Autowired
    private DonorService donorService;

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
        } catch (IllegalArgumentException e) {
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

    @GetMapping("/donated")
    public Response getDonations(@RequestAttribute User user) {
        if (user.getDonor() != null) {
            return new Response(200, true, donorService.getAllDonations(user.getId()));
        } else {
            return new Response(403, false, "Current user is not a donor");
        }
    }
}