package swp.medichor.model.response;

import java.time.LocalDate;
import lombok.Getter;
import swp.medichor.enums.Sex;
import swp.medichor.model.Donor;

@Getter
public class DonorResponse {

    private Integer userId;
    private String name;
    private LocalDate birthday;
    private Sex sex;
    private String identityNum;
    private String avatar;
    private String bloodType;
    private String anamnesis;
    private UserResponse user;

    public DonorResponse(Donor donor) {
        this.userId = donor.getUserId();
        this.name = donor.getName();
        this.birthday = donor.getBirthday();
        this.sex = donor.getSex();
        this.identityNum = donor.getIdentityNum();
        this.avatar = donor.getAvatar();
        this.bloodType = donor.getBloodType();
        this.anamnesis = donor.getAnamnesis();
        this.user = new UserResponse(donor.getUser());
    }
}
