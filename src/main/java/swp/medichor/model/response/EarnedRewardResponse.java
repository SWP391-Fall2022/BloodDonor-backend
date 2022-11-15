package swp.medichor.model.response;

import java.sql.Date;
import lombok.Getter;
import swp.medichor.model.EarnedReward;

@Getter
public class EarnedRewardResponse {

    private DonorResponse donor;
    private RewardResponse reward;
    private Date receiveDate;

    public EarnedRewardResponse(EarnedReward earnedReward) {
        donor = new DonorResponse(earnedReward.getDonor());
        reward = new RewardResponse(earnedReward.getReward());
        receiveDate = earnedReward.getReceiveDate();
    }
}
