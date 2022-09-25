package swp.medichor.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private Date birthday;
    private String sex;
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
}
