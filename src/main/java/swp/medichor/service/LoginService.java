package swp.medichor.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
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
import org.springframework.stereotype.Service;
import swp.medichor.jwt.JwtTokenProvider;
import swp.medichor.model.CustomUserDetails;

@Service
public class LoginService {

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

    public String authenicateUser(String usernameEmail, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        usernameEmail,
                        password)
        );

        // No exception means login request is valid
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
    }

    public ResponseEntity<?> authenticateGoogle(String idTokenString) throws Exception {
        GoogleIdToken idToken = verifier.verify(idTokenString);

        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = payload.getEmail();
            
            try {
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

                // If email is valid, check account's status
                if (userDetails.isAccountNonExpired() && userDetails.isAccountNonLocked()
                        && userDetails.isCredentialsNonExpired() && userDetails.isEnabled()) {
                    UsernamePasswordAuthenticationToken authentication
                            = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    String jwt = tokenProvider.generateToken(userDetails);
                    return ResponseEntity.ok(jwt);
                }

                // Account cannot be accessed
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            } catch (UsernameNotFoundException e) {
                // Email is valid but account doesn't exist in database,
                // should redirect to register page.
                // Generate jwt for validate registered email later.
                long expiration = 1000 * 60 * 60; // 1 hour
                String jwt = tokenProvider.generateToken(email, expiration);
                return ResponseEntity.status(HttpStatus.FOUND).body(jwt);
            }
        } else {
            throw new GeneralSecurityException("Invalid ID token");
        }
    }
}
