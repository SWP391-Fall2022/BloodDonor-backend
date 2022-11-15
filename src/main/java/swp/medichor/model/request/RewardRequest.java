package swp.medichor.model.request;

import java.sql.Date;
import lombok.Getter;

@Getter
public class RewardRequest {

    private Date expiredDate;
    private Integer level;
    private String sponsor;
    private String code;
    private Boolean status = true;
    private String details;
    private Integer amount;
}
