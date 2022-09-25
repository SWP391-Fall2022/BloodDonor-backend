package swp.medichor.model.compositekey;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DonateRegistrationKey implements Serializable {

    private Integer donorId;
    private Integer campaignId;
    private Date registeredDate;
}
