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
@Table(name = "Post")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PostId")
    private Integer id;

    private Timestamp postingTime;
    private String title;
    private String author;
    private String content;
    private String images;
    private Integer category;

    @ManyToOne
    @JoinColumn(name = "UserId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User postingUser;
}
