package swp.medichor.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import java.util.Collections;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import swp.medichor.enums.Role;
import swp.medichor.jwt.JwtTokenProvider;
import swp.medichor.model.CustomUserDetails;
import swp.medichor.model.response.LoginResponse;
import swp.medichor.model.response.Response;

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

    public Response authenticateUser(String usernameEmail, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            usernameEmail,
                            password)
            );

            // No exception means login request is valid
            SecurityContextHolder.getContext().setAuthentication(authentication);
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            String token = tokenProvider.generateToken(userDetails);
            Role role = userDetails.getUser().getRole();
            return new Response(200, true, new LoginResponse(token, role));
        } catch (BadCredentialsException ex) {
            return new Response(401, false, "Tên đăng nhập hoặc mật khẩu không chính xác");
        } catch (LockedException ex) {
            return new Response(401, false, "Tài khoản đã bị khóa");
        } catch (DisabledException ex) {
            return new Response(401, false, "Tài khoản chưa được xác nhận hay xác minh bởi quản trị viên");
        }
    }

    public Response authenticateGoogle(String idTokenString) throws Exception {
        GoogleIdToken idToken = verifier.verify(idTokenString);

        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = payload.getEmail();

            try {
                CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(email);

                // If email is valid, check account's status
                if (userDetails.isAccountNonExpired() && userDetails.isAccountNonLocked()
                        && userDetails.isCredentialsNonExpired() && userDetails.isEnabled()) {
                    UsernamePasswordAuthenticationToken authentication
                            = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    String token = tokenProvider.generateToken(userDetails);
                    Role role = userDetails.getUser().getRole();
                    return new Response(200, true, new LoginResponse(token, role));
                }

                // Account cannot be accessed
                return new Response(403, false, "Tài khoản bị vô hiệu hoá hoặc chưa được xác minh");
            } catch (UsernameNotFoundException e) {
                // Email is valid but account doesn't exist in database,
                // should redirect to register page.
                // Generate jwt for validate registered email later.
                long expiration = 1000 * 60 * 60; // 1 hour
                String jwt = tokenProvider.generateToken(email, expiration);
//                return ResponseEntity.status(HttpStatus.FOUND).body(jwt);
                return new Response(302, true, jwt);
            }

        } else {
            // Invalid idToken
            return new Response(403, false, "Đã xảy ra lỗi khi xác thực với Google");
        }

    }
}
