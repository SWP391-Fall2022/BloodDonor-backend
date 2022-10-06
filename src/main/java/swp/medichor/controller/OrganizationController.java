package swp.medichor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swp.medichor.model.User;
import swp.medichor.model.request.UpdateAvatarRequest;
import swp.medichor.model.request.UpdateInfoOrganizationRequest;
import swp.medichor.model.response.Response;
import swp.medichor.service.OrganizationServive;

@RestController
@RequestMapping("/v1/organization")
public class OrganizationController {

    @Autowired
    private OrganizationServive organizationServive;

    @GetMapping("/getInfo")
    public Response getInfoOfOneOrganization(@RequestAttribute User user) {
        return organizationServive.getInfoOfOne(user.getOrganization());
    }

    @PutMapping("/updateInfo")
    public Response updateInfoOfOneOrganization(@RequestAttribute User user,
                                                @RequestBody UpdateInfoOrganizationRequest request) {
        return organizationServive.updateInfoOfOne(user.getOrganization().getUserId(), request);
    }

    @PutMapping("/updateAvatar")
    public Response updateAvatarOrganization(@RequestAttribute User user,
                                             @RequestBody UpdateAvatarRequest request) {
        return organizationServive.updateAvatar(user.getOrganization().getUserId(), request);
    }

}
