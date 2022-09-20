package swp.medichor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swp.medichor.jwt.JwtTokenProvider;
import swp.medichor.model.CustomUserDetails;
import swp.medichor.model.LoginRequest;

@RestController
@RequestMapping("/v1/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping
    public String authenticateUser(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword())
        );

        // No exception means login request is valid
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
    }
    
    @GetMapping("/test")
    public String onLoginSuccess() {
        return "*** LOGed IN***";
    }
}
