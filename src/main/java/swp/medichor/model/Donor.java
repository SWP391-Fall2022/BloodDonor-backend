package swp.medichor.model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "Donor")
@Data
@Builder
public class Donor implements Serializable {

    @Id
    @OneToOne
    @JoinColumn(name = "UserId")
    private User user;

    private String name;
    private Date birthday;
    private String sex;
    private String identityNum;
    private String avatar;
    private String bloodType;
    private String anamnesis;
}
