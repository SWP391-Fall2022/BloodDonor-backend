package swp.medichor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.medichor.enums.Approve;
import swp.medichor.enums.Role;
import swp.medichor.model.*;
import swp.medichor.model.request.RegisterDonorRequest;
import swp.medichor.model.request.RegisterOrganizationRequest;
import swp.medichor.model.response.RegisterInfo;
import swp.medichor.model.response.Response;
import swp.medichor.utils.EmailPlatform;
import swp.medichor.utils.Validator;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RegisterService {
    private final String FROM = "nvtien1602.forwork@gmail.com";
    private final String SUBJECT = "CONFIRM YOUR CODE";

    @Autowired
    private UserService userService;
    @Autowired
    private OrganizationServive organizationServive;
    @Autowired
    private DonorService donorService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private VerificationCodeService verificationCodeService;
    @Autowired
    private EmailService emailService;

    @Transactional
    public Response registerOrganization(RegisterOrganizationRequest request) {
        if (!request.getRole().equals(Role.ORGANIZATION))
            return new Response(400, false, "Invalid role");
        if (!Validator.testEmail(request.getEmail())) {
            return new Response(400, false, "Email not valid");
        }
        if (!Validator.testPhoneNumber(request.getPhone())) {
            return new Response(400, false, "Phone number not valid");
        }
        if (!request.getConfirmPassword().equals(request.getPassword())) {
            return new Response(400, false, "Confirm password not match");
        }
        Optional<User> user = userService.getUserByEmailAndUsername(request.getUsername(), request.getEmail());
        if (user.isPresent() && user.get().getVerificationCode().getConfirmed()) {
            return new Response(400, false, "Account already registered");
        }
        else if (user.isPresent() && !user.get().getVerificationCode().getConfirmed()) {
            return resendCode(user.get().getId());
        }

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
        emailService.send(FROM, newUser.getEmail(), SUBJECT, EmailPlatform.buildConfirmCodeEmail(request.getName(), code));
        return new Response(200, true, new RegisterInfo(
                newUser.getId(),
                "Register successfully"
        ));
    }

    @Transactional
    public Response registerDonor(RegisterDonorRequest request) {
        if (!request.getRole().equals(Role.DONOR))
            return new Response(400, false, "Invalid role");
        if (!Validator.testEmail(request.getEmail())) {
            return new Response(400, false, "Email not valid");
        }
        if (!Validator.testPhoneNumber(request.getPhone())) {
            return new Response(400, false, "Phone number not valid");
        }
        if (!request.getConfirmPassword().equals(request.getPassword())) {
            return new Response(400, false, "Confirm password not match");
        }
        if (request.getBirthday().isAfter(LocalDate.now())) {
            return new Response(400, false, "Invalid birthday");
        }
        Optional<User> user = userService.getUserByEmailAndUsername(request.getUsername(), request.getEmail());
        if (user.isPresent() && user.get().getVerificationCode().getConfirmed()) {
            return new Response(400, false, "Account already registered");
        }
        else if (user.isPresent() && !user.get().getVerificationCode().getConfirmed()) {
            return resendCode(user.get().getId());
        }

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
        emailService.send(FROM, newUser.getEmail(), SUBJECT, EmailPlatform.buildConfirmCodeEmail(request.getName(), code));
        return new Response(200, true, new RegisterInfo(
                newUser.getId(),
                "Register successfully"
        ));

    }

    @Transactional
    public Response confirmCode(int code) {
        Optional<VerificationCode> existVerificationCode = verificationCodeService.getVerificationCodeByCode(code);
        if (existVerificationCode.isEmpty())
            return new Response(400, false, "Invalid code");
        VerificationCode verificationCode = existVerificationCode.get();
        if (verificationCode.getExpiresAt().isBefore(LocalDateTime.now())) {
            return new Response(406, false, "Code expires");
        }
        verificationCodeService.setConfirmsAt(verificationCode);
        userService.enableUser(verificationCode.getUser());
        return new Response(200, true, "Confirmed");
    }

    @Transactional
    public Response resendCode(Integer userId) {
        Optional<User> existUser = userService.getUserById(userId);
        if (existUser.isEmpty()) return new Response(400, false, "ID not found");
        User user = existUser.get();
        VerificationCode verificationCode = verificationCodeService.getVerificationCodeById(userId).get();
        if (verificationCode.getConfirmed()) {
            return new Response(400, false, "Already registered");
        }
        int code = verificationCodeService.alterVerificationCode(verificationCode);
        String name = "null";
        if (user.getRole().equals(Role.ORGANIZATION)) name = user.getOrganization().getName();
        else if (user.getRole().equals(Role.DONOR)) name = user.getDonor().getName();
        emailService.send(FROM, user.getEmail(), SUBJECT, EmailPlatform.buildConfirmCodeEmail(name, code));
        return new Response(200, true, new RegisterInfo(
                userId,
                "Register successfully"
        ));
    }
}
