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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "District")
@Data
public class District implements Serializable {

    @Id
    private int id;

    private String name;
    private String prefix;

    @ManyToOne
    @JoinColumn(name = "provinceId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Province province;

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<User> users;
}
