package swp.medichor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swp.medichor.model.User;
import swp.medichor.model.response.DonorResponse;
import swp.medichor.model.response.Response;

@RestController
@RequestMapping("/v1/donors/me")
public class CurrentDonorController {

    @GetMapping
    public Response getProfile(@RequestAttribute User user) {
        if (user != null && user.getDonor() != null) {
            return new Response(200, true, new DonorResponse(user.getDonor()));
        }
        return new Response(404, false, null);
    }

}
