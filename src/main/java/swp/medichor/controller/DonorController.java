package swp.medichor.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swp.medichor.model.Donor;
import swp.medichor.model.User;
import swp.medichor.model.request.QuestionRequest;
import swp.medichor.model.response.DonorResponse;
import swp.medichor.model.response.Response;
import swp.medichor.service.DonorService;

@RestController
@RequestMapping("/v1/donors")
public class DonorController {

    @Autowired
    private DonorService donorService;

    @GetMapping
    public Response getAllDonors() {
        return new Response(200, true, donorService.getAll());
    }

    @GetMapping("/{id}")
    public Response getDonor(@PathVariable int id) {
        Optional<Donor> donor = donorService.findById(id);
        if (donor.isPresent()) {
            return new Response(200, true, new DonorResponse(donor.get()));
        }
        return new Response(404, false, null);
    }

//    @GetMapping("/{id}/registered/count")
//    public Response countRegisteredCampaigns(@PathVariable int id) {
//        return new Response(200, true, donorService.countRegisteredCampaigns(id));
//    }

    @GetMapping("/{id}/donated/count")
    public Response countParticipatedCampaigns(@PathVariable int id) {
        return new Response(200, true, donorService.countParticipatedCampaigns(id));
    }

    @GetMapping("/{id}/bloodAmount")
    public Response getTotalAmountOfBlood(@PathVariable int id) {
        return new Response(200, true, donorService.getTotalAmountOfBlood(id));
    }

    @PostMapping("/question/{campaignId}")
    public Response addQuestion(@RequestAttribute User user,
                                @PathVariable("campaignId") Integer campaignId,
                                @RequestBody QuestionRequest request) {
        return donorService.addQuestion(user, campaignId, request);
    }
}
