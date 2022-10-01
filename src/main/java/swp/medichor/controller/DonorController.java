package swp.medichor.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swp.medichor.model.Donor;
import swp.medichor.model.response.DonorResponse;
import swp.medichor.model.response.Response;
import swp.medichor.service.DonorService;

@RestController
@RequestMapping("/v1/donors")
public class DonorController {

    @Autowired
    private DonorService donorService;

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
}
