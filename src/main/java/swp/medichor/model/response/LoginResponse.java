package swp.medichor.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import swp.medichor.enums.Role;

@AllArgsConstructor
@Getter
public class LoginResponse {

    String token;
    Role role;
}
