package swp.medichor.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import swp.medichor.enums.Role;


@Entity
@Table(name = "[User]")
@NoArgsConstructor
@AllArgsConstructor
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

    @Builder.Default
    private Boolean status = true;
    @Builder.Default
    private Boolean enabled = false;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Donor donor;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Organization organization;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private VerificationCode verificationCode;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Message> senderMessages;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Message> receiverMessages;

    @OneToMany(mappedBy = "postingUser", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Post> posts;

    public User(String username, String password, String phone, String email, District district, String addressDetails, Role role, Boolean status, Boolean enabled) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.district = district;
        this.addressDetails = addressDetails;
        this.role = role;
        this.status = status;
        this.enabled = enabled;
    }
}
