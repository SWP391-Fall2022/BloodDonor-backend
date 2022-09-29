package swp.medichor.model.request;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import swp.medichor.enums.Sex;
import swp.medichor.model.Donor;

@NoArgsConstructor
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

    public UpdateDonorRequest(Donor donor) {
        this.name = donor.getName();
        this.birthday = donor.getBirthday();
        this.sex = donor.getSex();
        this.identityNum = donor.getIdentityNum();
        this.avatar = donor.getAvatar();
        this.bloodType = donor.getBloodType();
        this.anamnesis = donor.getAnamnesis();
        this.user = new UpdateUserRequest(donor.getUser());
    }
}
