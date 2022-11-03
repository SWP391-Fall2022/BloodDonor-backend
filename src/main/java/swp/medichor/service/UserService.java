package swp.medichor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;
import swp.medichor.enums.Approve;
import swp.medichor.enums.Role;
import swp.medichor.model.CustomUserDetails;
import swp.medichor.model.Organization;
import swp.medichor.model.User;
import swp.medichor.model.request.ChangePasswordRequest;
import swp.medichor.model.request.UpdateAvatarRequest;
import swp.medichor.model.response.Response;
import swp.medichor.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        user = userRepository.findByEmail(user.getEmail()).get();
        return user;
    }

    public void enableUser(User user) {
        if (user.getRole().equals(Role.ORGANIZATION) &&
                (user.getOrganization().getApprove().equals(Approve.PENDING) ||
                user.getOrganization().getApprove().equals(Approve.REJECTED))) {
            user.setEnabled(false);
        }
        else user.setEnabled(true);
    }

    public Optional<User> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> getUserByEmailAndUsername(String username, String email) {
        return userRepository.findByUsernameAndEmail(username, email);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllDonorsByDistrictId(Integer districtId) {
        return userRepository.findByDistrictId(districtId, Role.DONOR);
    }

    public List<User> getAllDonorsByDistrictIdByBloodType(Integer districtId, String bloodType) {
        return userRepository.findByDistrictIdAndBloodType(districtId, Role.DONOR, bloodType);
    }

    @Transactional
    public Response updateAvatar(Integer userId, UpdateAvatarRequest request) {
        Optional<User> isExistUser = userRepository.findById(userId);
        if (isExistUser.isEmpty())
            return new Response(400, false, "ID not found");
        User user = isExistUser.get();
        if (!user.getStatus() || !user.getEnabled()) {
            return new Response(403, false, "The account is disabled or unverified");
        }

        if (user.getRole().equals(Role.DONOR)) {
            user.getDonor().setAvatar(request.getAvatar());
        }
        else if (user.getRole().equals(Role.ORGANIZATION)) {
            if (user.getOrganization().getApprove().equals(Approve.PENDING) || user.getOrganization().getApprove().equals(Approve.REJECTED))
                return new Response(403, false, "The account is disabled or unverified");
            user.getOrganization().setAvatar(request.getAvatar());
        }
        return new Response(200, true, "Update avatar successfully");
    }

    @Transactional
    public Response updatePassword(Integer userId, ChangePasswordRequest request) {
        Optional<User> isExistUser = userRepository.findById(userId);
        if (isExistUser.isEmpty())
            return new Response(400, false, "ID not found");
        User user = isExistUser.get();
        if (!user.getStatus() || !user.getEnabled()) {
            return new Response(403, false, "The account is disabled or unverified");
        }
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword()))
            return new Response(400, false, "Old password is incorrect.");
        if (!request.getNewPassword().equals(request.getConfirmNewPassword()))
            return new Response(400, false, "Confirm password not match");

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        return new Response(200, true, "Update password successfully");
    }

}
