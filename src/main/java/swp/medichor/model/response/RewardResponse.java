package swp.medichor.model.response;

import java.sql.Date;
import lombok.Getter;
import swp.medichor.model.Reward;

@Getter
public class RewardResponse {

    private Integer id;
    private Date expiredDate;
    private Integer level;
    private String sponsor;
    private String code;
    private Boolean status;
    private String details;
    private Integer amount;

    public RewardResponse(Reward reward) {
        id = reward.getId();
        expiredDate = reward.getExpiredDate();
        level = reward.getLevel();
        sponsor = reward.getSponsor();
        code = reward.getCode();
        status = reward.getStatus();
        details = reward.getDetails();
        amount = reward.getAmount();
    }
}
