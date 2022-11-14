package swp.medichor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;
import swp.medichor.enums.Approve;
import swp.medichor.enums.Role;
import swp.medichor.model.*;
import swp.medichor.model.request.ChangePasswordRequest;
import swp.medichor.model.request.RegisterDonorRequest;
import swp.medichor.model.request.RegisterOrganizationRequest;
import swp.medichor.model.response.RegisterResponse;
import swp.medichor.model.response.Response;
import swp.medichor.utils.EmailPlatform;
import swp.medichor.utils.Validator;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RegisterService {
    private final String FROM = "medichorvn@gmail.com";
    private final String SUBJECT = "XÁC NHẬN MÃ ĐĂNG KÝ";

    private final Argon2PasswordEncoder passwordEncoder =  new Argon2PasswordEncoder();
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
            return new Response(400, false, "Vai trò không hợp lệ");
        if (!Validator.testName(request.getUsername()))
            return new Response(400, false, "Kí tự trong tên đăng nhập không phù hợp");
        if (!Validator.testName(request.getName()))
            return new Response(400, false, "Kí tự trong tên không phù hợp");
        if (!Validator.testEmail(request.getEmail())) {
            return new Response(400, false, "Email không hợp lệ");
        }
        if (!Validator.testPhoneNumber(request.getPhone())) {
            return new Response(400, false, "Số điện thoại không hợp lệ");
        }
        if (!request.getConfirmPassword().equals(request.getPassword())) {
            return new Response(400, false, "Mật khẩu nhập lại không khớp với mật khẩu gốc");
        }
        Optional<User> user = userService.getUserByEmailAndUsername(request.getUsername(), request.getEmail());
        if (user.isPresent() && user.get().getVerificationCode().getConfirmed()) {
            return new Response(400, false, "Tài khoản đã được đăng ký");
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
        return new Response(200, true, new RegisterResponse(
                newUser.getId(),
                "Đăng ký tài khoản dành cho tổ chức y tế thành công"
        ));
    }

    @Transactional
    public Response registerDonor(RegisterDonorRequest request) {
        if (!request.getRole().equals(Role.DONOR))
            return new Response(400, false, "Vai trò không hợp lệ");
        if (!Validator.testName(request.getUsername()))
            return new Response(400, false, "Kí tự trong tên đăng nhập không phù hợp");
        if (!Validator.testName(request.getName()))
            return new Response(400, false, "Kí tự trong tên không phù hợp");
        if (!Validator.testEmail(request.getEmail())) {
            return new Response(400, false, "Email không hợp lệ");
        }
        if (!Validator.testPhoneNumber(request.getPhone())) {
            return new Response(400, false, "Số điện thoại không hợp lệ");
        }
        if (!request.getConfirmPassword().equals(request.getPassword())) {
            return new Response(400, false, "Mật khẩu nhập lại không khớp với mật khẩu gốc");
        }
        if (request.getBirthday().isAfter(LocalDate.now())) {
            return new Response(400, false, "Ngày sinh không hợp lệ");
        }
        LocalDate now = LocalDate.now();
        if (now.getYear() - request.getBirthday().getYear() < 18 || now.getYear() - request.getBirthday().getYear() > 60)
            return new Response(400, false, "Tuổi của tình nguyện viên phải từ 18 tới 60 tuổi");

        Optional<User> user = userService.getUserByEmailAndUsername(request.getUsername(), request.getEmail());
        if (user.isPresent() && user.get().getVerificationCode().getConfirmed()) {
            return new Response(400, false, "Tài khoản đã được đăng ký");
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
        return new Response(200, true, new RegisterResponse(
                newUser.getId(),
                "Đăng ký tài khoản cho tình nguyện viên thành công"
        ));

    }

    @Transactional
    public Response confirmCode(int code) {
        Optional<VerificationCode> existVerificationCode = verificationCodeService.getVerificationCodeByCode(code);
        if (existVerificationCode.isEmpty())
            return new Response(400, false, "Mã xác nhận không hợp lệ");
        VerificationCode verificationCode = existVerificationCode.get();
        if (verificationCode.getExpiresAt().isBefore(LocalDateTime.now())) {
            return new Response(406, false, "Mã xác nhận đã hết hạn");
        }
        verificationCodeService.setConfirmsAt(verificationCode);
        userService.enableUser(verificationCode.getUser());
        return new Response(200, true, new RegisterResponse(
                verificationCode.getUserId(),
                "Xác nhận mã thành công"
        ));
    }

    @Transactional
    public Response resendCode(Integer userId) {
        Optional<User> existUser = userService.getUserById(userId);
        if (existUser.isEmpty()) return new Response(400, false, "ID không tồn tại");
        User user = existUser.get();
        VerificationCode verificationCode = verificationCodeService.getVerificationCodeById(userId).get();
        if (verificationCode.getConfirmed()) {
             return new Response(400, false, "Tài khoản đã xác nhận mã");
        }
        int code = verificationCodeService.alterVerificationCode(verificationCode);
        String name = "null";
        if (user.getRole().equals(Role.ORGANIZATION)) name = user.getOrganization().getName();
        else if (user.getRole().equals(Role.DONOR)) name = user.getDonor().getName();
        if (!user.getEnabled())
            emailService.send(FROM, user.getEmail(), SUBJECT, EmailPlatform.buildConfirmCodeEmail(name, code));
        else
            emailService.send(FROM, user.getEmail(), SUBJECT, EmailPlatform.buildConfirmCodeEmailForChangePass(name, code));
        return new Response(200, true, new RegisterResponse(
                userId,
                "Gửi lại mã xác nhận thành công"
        ));
    }

    @Transactional
    public Response getAccountByEmailToChangePassword(String email) {
        Optional<User> isExistUser = userService.getUserByEmail(email);
        if (isExistUser.isEmpty() || !isExistUser.get().getEnabled())
            return new Response(400, false, "Email này chưa được đăng ký tài khoản");
        if (!isExistUser.get().getStatus())
            return new Response(401, false, "Tài khoản của bạn đã bị khóa");
        User user = isExistUser.get();
        VerificationCode verificationCode = verificationCodeService.getVerificationCodeById(user.getId()).get();
        int code = verificationCodeService.alterVerificationCode(verificationCode);
        String name = "null";
        if (user.getRole().equals(Role.ORGANIZATION)) name = user.getOrganization().getName();
        else if (user.getRole().equals(Role.DONOR)) name = user.getDonor().getName();
        emailService.send(FROM, user.getEmail(), SUBJECT, EmailPlatform.buildConfirmCodeEmailForChangePass(name, code));
        return new Response(200, true, new RegisterResponse(
                user.getId(),
                "Tài khoản tồn tại. Bước tiếp theo là xác nhận mã"
        ));
    }

    @Transactional
    public Response changePasswordWhenForgetting(Integer userId, ChangePasswordRequest request) {
        if (!request.getNewPassword().equals(request.getConfirmNewPassword()))
            return new Response(400, false, "Mật khẩu nhập lại không khớp với mật khẩu gốc");
        Optional<User> existUser = userService.getUserById(userId);
        if (existUser.isEmpty()) return new Response(400, false, "ID không tồn tại");
        User user = existUser.get();
        VerificationCode verificationCode = verificationCodeService.getVerificationCodeById(userId).get();
        if (!verificationCode.getConfirmed())
            return new Response(403, false, "Tài khoản chưa được xác nhận mã lúc đăng ký");
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        return new Response(200, true, "Thay đổi mật khẩu thành công");


    }
}
