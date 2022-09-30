package swp.medichor.model.request;

import java.time.LocalDate;
import lombok.Getter;
import swp.medichor.enums.Sex;

@Getter
public class UpdateDonorRequest {

    private String name;
    private LocalDate birthday;
    private Sex sex;
    private String identityNum;
    private String avatar;
    private String bloodType;
    private String anamnesis;
    private UpdateUserRequest user;
}
