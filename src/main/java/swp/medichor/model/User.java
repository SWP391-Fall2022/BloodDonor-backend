package swp.medichor.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import swp.medichor.enums.Role;

@Entity
@Table(name = "Users")
@Data
@Builder
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    private String phone;
    private String email;

    @ManyToOne
    @JoinColumn(name = "DistrictId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private District district;

    private String addressDetails;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean status;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Donor donor;
}
