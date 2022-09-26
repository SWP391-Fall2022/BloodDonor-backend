package swp.medichor.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swp.medichor.model.request.RegisterOrganizationRequest;
import swp.medichor.service.RegisterService;

@RestController
@RequestMapping("/v1/register")
@AllArgsConstructor
public class RegisterController {
    @Autowired
    private final RegisterService registerService;
    @PostMapping("/organization")
    public String registerOrganization(@RequestBody RegisterOrganizationRequest request) {
        return registerService.registerOrganization(request);
    }
}
