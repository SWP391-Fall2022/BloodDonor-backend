package swp.medichor.model.compositekey;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EarnedRewardKey implements Serializable {

    private Integer donorId;
    private Integer rewardId;
}
