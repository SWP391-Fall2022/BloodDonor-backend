package swp.medichor.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Campaign")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Campaign implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String images;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean emergency;

    @Builder.Default
    private Boolean status = true;

    @ManyToOne
    @JoinColumn(name = "DistrictId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private District district;

    private String addressDetails;

    @ManyToOne
    @JoinColumn(name = "OrganizationId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Organization organization;

    @ManyToMany(mappedBy = "likedCampaigns", cascade = CascadeType.MERGE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Donor> likingDonors;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Question> questions;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<DonateRegistration> registrations;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<DonateRecord> record;
}
