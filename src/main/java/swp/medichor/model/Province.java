package swp.medichor.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Province")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Province implements Serializable {

    @Id
    private Integer id;
    private String name;
    private String code;

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<District> districts;
}
