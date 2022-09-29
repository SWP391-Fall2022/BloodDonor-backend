package swp.medichor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swp.medichor.model.User;
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
}
