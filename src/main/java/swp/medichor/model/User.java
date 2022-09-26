package swp.medichor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements Serializable {

    public enum Role {
        ROLE_ADMIN,
        ROLE_ORGANIZATION,
        ROLE_DONOR
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String phone;
    private String email;
    @ManyToOne
    @JoinColumn(name = "DistrictId")
    private District district;
    private String addressDetails;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Boolean status = false;
    private Boolean enabled = false;
}
