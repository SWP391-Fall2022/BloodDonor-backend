package swp.medichor.model;

import java.io.Serializable;
import java.sql.Date;
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
import swp.medichor.model.compositekey.EarnedRewardKey;

@Entity
@Table(name = "EarnedReward")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EarnedReward implements Serializable {

    @EmbeddedId
    private EarnedRewardKey id;

    @ManyToOne
    @JoinColumn(name = "DonorId")
    @MapsId("donorId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Donor donor;

    @ManyToOne
    @JoinColumn(name = "RewardId")
    @MapsId("rewardId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Reward reward;

    private Date receiveDate;
}
