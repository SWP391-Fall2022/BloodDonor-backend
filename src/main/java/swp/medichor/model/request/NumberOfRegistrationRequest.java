package swp.medichor.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import swp.medichor.enums.Period;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class NumberOfRegistrationRequest {
    private Period period;
    private LocalDate registeredDate;
}
