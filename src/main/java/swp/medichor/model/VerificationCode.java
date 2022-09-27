package swp.medichor.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "VerificationCode")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class VerificationCode {
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
