package swp.medichor.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swp.medichor.model.request.RegisterDonorRequest;
import swp.medichor.model.request.RegisterOrganizationRequest;
import swp.medichor.model.response.Response;
import swp.medichor.service.RegisterService;

@RestController
@RequestMapping("/v1/register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @PostMapping("/organization")
    public Response registerOrganization(@RequestBody RegisterOrganizationRequest request) {
        return registerService.registerOrganization(request);
    }

    @PostMapping("/donor")
    public Response registerDonor(@RequestBody RegisterDonorRequest request) {
        return registerService.registerDonor(request);
    }

    @PutMapping(path = "/confirmCode/{code}")
    public Response confirmCode(@PathVariable("code") int code) {
        return registerService.confirmCode(code);
    }

    @PutMapping(path = "/resendCode/{userId}")
    public Response resendCode(@PathVariable("userId") Integer userId) {
        return registerService.resendCode(userId);
    }
}
