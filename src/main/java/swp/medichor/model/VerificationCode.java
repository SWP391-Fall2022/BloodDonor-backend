package swp.medichor.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "VerificationCode")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VerificationCode implements Serializable {
    @Id
    private Integer userId;
    @OneToOne
    @JoinColumn(
            nullable = false,
            name = "UserId"
    )
    @MapsId
    private User user;

    private int code;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private Boolean confirmed;

    public VerificationCode(User user, int code, LocalDateTime createdAt, LocalDateTime expiresAt,
                            Boolean confirmed) {
        this.user = user;
        this.code = code;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.confirmed = confirmed;
    }
}
