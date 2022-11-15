package swp.medichor.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import swp.medichor.enums.DonateRegistrationStatus;
import swp.medichor.enums.Period;
import swp.medichor.model.compositekey.DonateRegistrationKey;

@Entity
@Table(name = "DonateRegistration")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DonateRegistration implements Serializable {

    @EmbeddedId
    private DonateRegistrationKey id;

    @ManyToOne
    @JoinColumn(name = "DonorId")
    @MapsId("donorId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Donor donor;

    @ManyToOne
    @JoinColumn(name = "CampaignId")
    @MapsId("campaignId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Campaign campaign;

    @Enumerated(EnumType.STRING)
    private DonateRegistrationStatus status;

    @Enumerated(EnumType.STRING)
    private Period period;

    private String code;
}
