package swp.medichor.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swp.medichor.model.request.LoginRequest;
import swp.medichor.model.response.Response;
import swp.medichor.service.LoginService;

@RestController
@RequestMapping("/v1/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public Response authenticateUser(@RequestBody LoginRequest request) {
        return loginService.authenticateUser(
                request.getUsername(),
                request.getPassword());
    }

    @PostMapping("/google")
    public Response authenticateGoogle(@RequestBody String idTokenString) {
        try {
            return loginService.authenticateGoogle(idTokenString);
        } catch (Exception e) {
            // Cannot validate id_token
            return new Response(400, false, e.getLocalizedMessage());
        }
    }
}
