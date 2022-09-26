package swp.medichor.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
import swp.medichor.model.compositekey.DonateRecordKey;

@Entity
@Table(name = "DonateRecord")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DonateRecord implements Serializable {

    @EmbeddedId
    private DonateRecordKey id;

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

    private String details;
    private Boolean status;
    private String bloodType;
    private Integer amount;
    private Float weight;
}
