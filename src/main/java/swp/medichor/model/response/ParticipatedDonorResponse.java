package swp.medichor.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import swp.medichor.enums.Sex;

@AllArgsConstructor
@Getter
public class ParticipatedDonorResponse {
    private String name;
    private String identityNum;
    private Sex sex;
    private String bloodType;
    private String anamnesis;
    private DonateRegistrationResponse donateRegistrationResponse;
}
