package swp.medichor.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swp.medichor.model.request.RegisterDonorRequest;
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

    @PostMapping("/donor")
    public String registerDonor(@RequestBody RegisterDonorRequest request) {
        return registerService.registerDonor(request);
    }

    @PutMapping(path = "/confirmCode/{code}")
    public String confirmCode(@PathVariable("code") int code) {
        return registerService.confirmCode(code);
    }

    @PutMapping(path = "/resendCode/{userId}")
    public String resendCode(@PathVariable("userId") Integer userId) {
        return registerService.resendCode(userId);
    }
}
