package swp.medichor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.medichor.model.User;
import swp.medichor.model.VerificationCode;
import swp.medichor.repository.VerificationCodeRepository;
import swp.medichor.utils.Random;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VerificationCodeService {
    @Autowired
    private VerificationCodeRepository verificationCodeRepository;

    public Optional<VerificationCode> getVerificationCodeByCode(int code) {
        return verificationCodeRepository.findByCode(code);
    }

    public Optional<VerificationCode> getVerificationCodeById(int id) {
        return verificationCodeRepository.findById(id);
    }

    public int addVerificationCode(User user) {
        int code;
        do {
            code = Random.randomCode(100000, 999999);
        }
        while (verificationCodeRepository.findByCode(code).isPresent());
        VerificationCode verificationCode = new VerificationCode(
                user,
                code,
                LocalDateTime.now(),
                LocalDateTime.now().plusSeconds(300),
                false
        );
        verificationCodeRepository.save(verificationCode);
        return code;
    }

    //create new code

    public int alterVerificationCode(VerificationCode verificationCode) {
        int code;
        do {
            code = Random.randomCode(100000, 999999);
        }
        while (verificationCodeRepository.findByCode(code).isPresent());
        verificationCode.setCode(code);
        verificationCode.setCreatedAt(LocalDateTime.now());
        verificationCode.setExpiresAt(LocalDateTime.now().plusSeconds(300));
        verificationCode.setConfirmed(false);
        return code;
    }

    @Transactional
    public void setConfirmsAt(VerificationCode code) {
        code.setConfirmed(true);
    }
}
