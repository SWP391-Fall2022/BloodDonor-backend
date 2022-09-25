package swp.medichor.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Question")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QuestionId")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "DonorId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Donor donor;

    @ManyToOne
    @JoinColumn(name = "CampaignId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Campaign campaign;

    private Timestamp askTime;
    private String question;
    private String answer;

    @Builder.Default
    private Boolean status = false;
}
