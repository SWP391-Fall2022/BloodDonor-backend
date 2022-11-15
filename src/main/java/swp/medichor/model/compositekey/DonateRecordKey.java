package swp.medichor.model.compositekey;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DonateRecordKey implements Serializable {

    private Integer donorId;
    private Integer campaignId;
    private Date registeredDate;
}
