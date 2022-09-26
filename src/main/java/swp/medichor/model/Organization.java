package swp.medichor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "organization")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Organization {

    public enum Approve {
        APPROVE_YES,
        APPROVE_NO,
        APPROVE_PENDING
    }

    @Id
    @OneToOne
    @JoinColumn(
            nullable = false,
            name = "userId"
    )
    private User user;
    private String name;
    private String taxcode;
    @Enumerated(EnumType.STRING)
    private Approve approve;
    private String avatar;
    private String website;
    private String introduction;

}
