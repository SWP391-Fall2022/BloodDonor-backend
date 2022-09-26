package swp.medichor.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import swp.medichor.enums.Sex;

@Entity
@Table(name = "Donor")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Donor implements Serializable {

    @Id
    private Integer userId;

    @OneToOne
    @JoinColumn(name = "UserId")
    @MapsId
    private User user;

    private String name;
    private LocalDate birthday;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    private String identityNum;
    private String avatar;
    private String bloodType;
    private String anamnesis;

    @OneToMany(mappedBy = "donor", cascade = CascadeType.MERGE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<EarnedReward> earnedRewards;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "LikeRecord",
            joinColumns = @JoinColumn(name = "DonorId"),
            inverseJoinColumns = @JoinColumn(name = "CampaignId")
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Campaign> likedCampaigns;

    @OneToMany(mappedBy = "donor", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Question> questions;

    @OneToMany(mappedBy = "donor", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<DonateRegistration> registrations;

    @OneToMany(mappedBy = "donor", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<DonateRecord> record;

    public Donor(Integer userId, String name, LocalDate birthday, Sex sex, String identityNum, String avatar,
                 String bloodType,
                 String anamnesis) {
        this.userId = userId;
        this.name = name;
        this.birthday = birthday;
        this.sex = sex;
        this.identityNum = identityNum;
        this.avatar = avatar;
        this.bloodType = bloodType;
        this.anamnesis = anamnesis;
    }

    public Donor(User user, String name, LocalDate birthday, Sex sex, String identityNum, String avatar,
                 String bloodType, String anamnesis) {
        this.user = user;
        this.name = name;
        this.birthday = birthday;
        this.sex = sex;
        this.identityNum = identityNum;
        this.avatar = avatar;
        this.bloodType = bloodType;
        this.anamnesis = anamnesis;
    }
}
