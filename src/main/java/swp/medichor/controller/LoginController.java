package swp.medichor.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import java.security.GeneralSecurityException;
import java.util.Collections;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swp.medichor.jwt.JwtTokenProvider;
import swp.medichor.model.CustomUserDetails;
import swp.medichor.model.request.LoginRequest;
import swp.medichor.service.UserService;

@RestController
@RequestMapping("/v1/login")
public class LoginController {

    @Value("${google.client-id}")
    private String CLIENT_ID;
    private GoogleIdTokenVerifier verifier;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserService customUserDetailsService;

    @PostConstruct
    public void initVerifier() {
        verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList(CLIENT_ID))
                // Or, if multiple clients access the backend:
                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .build();
    }

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

    @PostMapping("/google")
    public ResponseEntity<?> authenticateGoogle(@RequestBody String idTokenString) {
        try {
            GoogleIdToken idToken = verifier.verify(idTokenString);
            if (idToken != null) {
                Payload payload = idToken.getPayload();
                String email = payload.getEmail();
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

                // If email is valid
                UsernamePasswordAuthenticationToken authentication
                        = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                String jwt = tokenProvider.generateToken(userDetails);

                return ResponseEntity.ok(jwt);
            } else {
                throw new GeneralSecurityException("Invalid ID token");
            }
        } catch (UsernameNotFoundException e) {
            // Login via Google sucess but app account doesn't exist,
            // should redirect to register page
            return ResponseEntity.status(HttpStatus.FOUND).body(e.getLocalizedMessage());
        } catch (Exception e) {
            // Cannot validate id_token
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }
}
