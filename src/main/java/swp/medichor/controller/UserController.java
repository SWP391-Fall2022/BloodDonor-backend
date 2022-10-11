package swp.medichor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swp.medichor.model.User;
import swp.medichor.model.request.ChangePasswordRequest;
import swp.medichor.model.request.UpdateAvatarRequest;
import swp.medichor.model.response.Response;
import swp.medichor.service.UserService;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/updateAvatar")
    public Response updateAvatar(@RequestAttribute User user,
                                 @RequestBody UpdateAvatarRequest request) {
        return userService.updateAvatar(user.getId(), request);
    }

    @PutMapping("/updatePassword")
    public Response updatePassword(@RequestAttribute User user,
                                   @RequestBody ChangePasswordRequest request) {
        return userService.updatePassword(user.getId(), request);
    }
}
