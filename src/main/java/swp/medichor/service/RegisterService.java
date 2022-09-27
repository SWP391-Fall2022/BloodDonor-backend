package swp.medichor.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import swp.medichor.enums.Approve;
import swp.medichor.enums.Role;
import swp.medichor.model.*;
import swp.medichor.model.request.RegisterDonorRequest;
import swp.medichor.model.request.RegisterOrganizationRequest;
import swp.medichor.utils.EmailPlatform;
import swp.medichor.utils.Validator;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RegisterService {
    private final String FROM = "nvtien1602.forwork@gmail.com";
    private final String SUBJECT = "CONFIRM YOUR CODE";
    private final UserService userService;
    private final OrganizationServive organizationServive;
    private final DonorService donorService;
    private final AddressService addressService;
    private final VerificationCodeService verificationCodeService;
    private final EmailService emailService;

    @Transactional
    public String registerOrganization(RegisterOrganizationRequest request) {
        if (!request.getRole().equals(Role.ORGANIZATION))
            throw new IllegalStateException("Invalid role");
        if (!Validator.testEmail(request.getEmail())) {
            throw new IllegalStateException("Email not valid");
        }
        if (!Validator.testPhoneNumber(request.getPhone())) {
            throw new IllegalStateException("Phone number not valid");
        }
        if (!request.getConfirmPassword().equals(request.getPassword())) {
            throw new IllegalStateException("Confirm password not match");
        }
        Optional<User> user = userService.getUserByEmailAndUsername(request.getUsername(), request.getEmail());
        if (user.isPresent() && user.get().getVerificationCode().getConfirmed()) {
            throw new IllegalStateException("Account already registered");
        }
        else if (user.isPresent() && !user.get().getVerificationCode().getConfirmed()) {
            return resendCode(user.get().getId());
        }
//        User user = userService.registerUser(new User(
//                request.getUsername(),
//                request.getPassword(),
//                request.getPhone(),
//                request.getEmail(),
//                addressService.getDistrictById(request.getDistrictId()),
//                request.getAddressDetails(),
//                request.getRole(),
//                true,
//                false
//        ));
        District district = District.builder().id(request.getDistrictId()).build();
        User newUser = userService.registerUser(User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .phone(request.getPhone())
                .email(request.getEmail())
                .district(district)
                .addressDetails(request.getAddressDetails())
                .role(request.getRole())
                .status(true)
                .enabled(false)
                .build());

        Boolean saveOrganization = organizationServive.registerOrganization(new Organization(
                newUser,
                request.getName(),
                request.getTaxcode(),
                Approve.PENDING,
                null,
                null,
                null
        ));

        int code = verificationCodeService.addVerificationCode(newUser);
        emailService.send(FROM, newUser.getEmail(), SUBJECT, EmailPlatform.buildEmail(request.getName(), code));
        return "Register successfully";
    }

    @Transactional
    public String registerDonor(RegisterDonorRequest request) {
        if (!request.getRole().equals(Role.DONOR))
            throw new IllegalStateException("Invalid role");
        if (!Validator.testEmail(request.getEmail())) {
            throw new IllegalStateException("Email not valid");
        }
        if (!Validator.testPhoneNumber(request.getPhone())) {
            throw new IllegalStateException("Phone number not valid");
        }
        if (!request.getConfirmPassword().equals(request.getPassword())) {
            throw new IllegalStateException("Confirm password not match");
        }
        if (request.getBirthday().isAfter(LocalDate.now())) {
            throw new IllegalStateException("Invalid birthday");
        }
        Optional<User> user = userService.getUserByEmailAndUsername(request.getUsername(), request.getEmail());
        if (user.isPresent() && user.get().getVerificationCode().getConfirmed()) {
            throw new IllegalStateException("Account already registered");
        }
        else if (user.isPresent() && !user.get().getVerificationCode().getConfirmed()) {
            return resendCode(user.get().getId());
        }

//        User user = userService.registerUser(new User(
//                request.getUsername(),
//                request.getPassword(),
//                request.getPhone(),
//                request.getEmail(),
//                addressService.getDistrictById(request.getDistrictId()),
//                request.getAddressDetails(),
//                request.getRole(),
//                true,
//                false
//        ));
        District district = District.builder().id(request.getDistrictId()).build();
        User newUser = userService.registerUser(User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .phone(request.getPhone())
                .email(request.getEmail())
                .district(district)
                .addressDetails(request.getAddressDetails())
                .role(request.getRole())
                .status(true)
                .enabled(false)
                .build());

        boolean saveDonor = donorService.registerDonor(new Donor(
                newUser,
                request.getName(),
                request.getBirthday(),
                request.getSex(),
                request.getIdentityNum(),
                null,
                null,
                null
        ));

        int code = verificationCodeService.addVerificationCode(newUser);
        emailService.send(FROM, newUser.getEmail(), SUBJECT, EmailPlatform.buildEmail(request.getName(), code));
        return "Register successfully";

    }

    @Transactional
    public String confirmCode(int code) {
        VerificationCode verificationCode = verificationCodeService.getVerificationCodeByCode(code)
                .orElseThrow(() -> {
                    return new IllegalStateException("Invalid code");
                });
        if (verificationCode.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Code expires");
        }
        verificationCodeService.setConfirmsAt(verificationCode);
        userService.enableUser(verificationCode.getUser());
        return "Confirmed";
    }

    @Transactional
    public String resendCode(Integer userId) {
        User user = userService.getUserById(userId).orElseThrow(() -> {
            return new IllegalStateException("ID not found");
        });
        VerificationCode verificationCode = verificationCodeService.getVerificationCodeById(userId).get();
        if (verificationCode.getConfirmed()) {
            throw new IllegalStateException("Already registered");
        }
        int code = verificationCodeService.alterVerificationCode(verificationCode);
        String name = "null";
        if (user.getRole().equals(Role.ORGANIZATION)) name = user.getOrganization().getName();
        else if (user.getRole().equals(Role.DONOR)) name = user.getDonor().getName();
        emailService.send(FROM, user.getEmail(), SUBJECT, EmailPlatform.buildEmail(name, code));
        return "Resend successfully";
    }
}
