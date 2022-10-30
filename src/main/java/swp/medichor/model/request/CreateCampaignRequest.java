package swp.medichor.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CreateCampaignRequest {
    private String name;
    private String images;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean emergency;
    private String bloodTypes;
    private Integer districtId;
    private String addressDetails;
    private boolean sendMail;

    //optional attributes
    private List<LocalDate> onSiteDates;
    private boolean weekRepetition;
    private boolean monthRepetition;
    private List<DayOfWeek> daysOfWeek;
    private List<Integer> daysOfMonth;
    private Integer weekNumber;

}
