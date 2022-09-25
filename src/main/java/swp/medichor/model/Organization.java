package swp.medichor.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import swp.medichor.enums.Approve;

@Entity
@Table(name = "Organization")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Organization implements Serializable {

    @Id
    private Integer userId;

    @OneToOne
    @JoinColumn(name = "UserID")
    @MapsId
    private User user;

    private String name;
    private String taxCode;

    @Enumerated(EnumType.STRING)
    private Approve approve;

    private String avatar;
    private String website;
    private String introduction;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Campaign> campaigns;
}
