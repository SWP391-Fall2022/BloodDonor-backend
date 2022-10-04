package swp.medichor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swp.medichor.model.request.UpdateAvatarRequest;
import swp.medichor.model.request.UpdateInfoOrganizationRequest;
import swp.medichor.model.response.Response;
import swp.medichor.service.OrganizationServive;

@RestController
@RequestMapping("/v1/organization")
public class OrganizationController {

    @Autowired
    private OrganizationServive organizationServive;

    @GetMapping("/getInfo/{organizationId}")
    public Response getInfoOfOneOrganization(@PathVariable("organizationId") Integer organizationId) {

        //PHAN QUYEN USER

        return organizationServive.getInfoOfOne(organizationId);
    }

    @PutMapping("/updateInfo/{organizationId}")
    public Response updateInfoOfOneOrganization(@PathVariable("organizationId") Integer organizationId,
                                                @RequestBody UpdateInfoOrganizationRequest request) {
        return organizationServive.updateInfoOfOne(organizationId, request);
    }

//    @PutMapping("/updateAvatar/{organizationId}")
//    public Response updateAvatarOrganization(@PathVariable("organizationId") Integer organizationId,
//                                                @RequestParam(name = "avatar", required = true) String avatar) {
//        return organizationServive.updateAvatar(organizationId, avatar);
//    }

    @PutMapping("/updateAvatar/{organizationId}")
    public Response updateAvatarOrganization(@PathVariable("organizationId") Integer organizationId,
                                             @RequestBody UpdateAvatarRequest request) {
        return organizationServive.updateAvatar(organizationId, request);
    }

}
