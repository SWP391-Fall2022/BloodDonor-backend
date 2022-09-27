package swp.medichor.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.medichor.model.User;
import swp.medichor.model.VerificationCode;
import swp.medichor.repository.VerificationCodeRepository;
import swp.medichor.utils.Random;

import javax.transaction.Transactional;
import java.time.LocalDate;
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
                LocalDateTime.now().plusMinutes(15),
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
        verificationCode.setCreatedAt(LocalDateTime.now().plusMinutes(15));
        return code;
    }

    @Transactional
    public void setConfirmsAt(VerificationCode code) {
        code.setConfirmed(true);
    }
}
