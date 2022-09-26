package swp.medichor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;
import swp.medichor.model.CustomUserDetails;
import swp.medichor.model.User;
import swp.medichor.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    private final Argon2PasswordEncoder passwordEncoder =  new Argon2PasswordEncoder();
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new CustomUserDetails(
                userRepository.findByUsernameOrEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException(username))
        );
    }

    public User registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalStateException("Username already exists");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalStateException("Email already exists");
        }
        user.setId(userRepository.findAll().size() + 1);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return user;
    }

}
