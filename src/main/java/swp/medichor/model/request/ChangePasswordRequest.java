package swp.medichor.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangePasswordRequest {
    private String oldPassword; //can be null
    private String newPassword;
    private String confirmNewPassword;
}
