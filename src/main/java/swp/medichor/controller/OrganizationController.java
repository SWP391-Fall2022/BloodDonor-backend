package swp.medichor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swp.medichor.model.User;
import swp.medichor.model.request.ChangePasswordRequest;
import swp.medichor.model.request.UpdateAvatarRequest;
import swp.medichor.model.request.UpdateOrganizationRequest;
import swp.medichor.model.response.Response;
import swp.medichor.service.OrganizationServive;

@RestController
@RequestMapping("/v1/organization")
public class OrganizationController {

    @Autowired
    private OrganizationServive organizationServive;

    @GetMapping("/getInfo/{organizationId}")
    public Response getInfoOfOneOrganization(@PathVariable("organizationId") Integer organizationId) {
        return organizationServive.getInfoOfOne(organizationId);
    }

    @PutMapping("/updateInfo")
    public Response updateInfoOfOneOrganization(@RequestAttribute User user,
                                                @RequestBody UpdateOrganizationRequest request) {
        return organizationServive.updateInfoOfOne(user.getOrganization().getUserId(), request);
    }

}
