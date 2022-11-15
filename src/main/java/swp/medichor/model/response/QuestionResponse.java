package swp.medichor.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class QuestionResponse {
    private Integer questionId;
    private Integer donorId;
    private String donorName;
    private Integer campaignId;
    private String campaignName;
    private Timestamp askTime;
    private String question;
    private String Answer;
    private boolean status;
}
