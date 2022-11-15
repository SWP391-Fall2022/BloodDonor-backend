package swp.medichor.model;

import lombok.*;
import swp.medichor.model.compositekey.LikeRecordKey;

import javax.persistence.*;

@Entity
@Table(name = "LikeRecord")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeRecord {
    @EmbeddedId
    private LikeRecordKey id;

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

}
