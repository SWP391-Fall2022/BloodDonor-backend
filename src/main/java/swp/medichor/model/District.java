package swp.medichor.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.*;

@Entity
@Table(name = "District")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class District implements Serializable {

    @Id
    private int id;

    private String name;
    private String prefix;

    @ManyToOne
    @JoinColumn(name = "ProvinceId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Province province;

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<User> users;

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Campaign> campaigns;
}
