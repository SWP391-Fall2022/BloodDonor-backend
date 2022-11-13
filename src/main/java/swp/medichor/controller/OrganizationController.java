package swp.medichor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import swp.medichor.model.User;
import swp.medichor.model.request.UpdateOrganizationRequest;
import swp.medichor.model.response.Response;
import swp.medichor.service.OrganizationServive;

@RestController
@RequestMapping("/v1/organization")
public class OrganizationController {

    @Autowired
    private OrganizationServive organizationServive;

    @GetMapping
    public Response getAllOrganization() {
        return new Response(200, true, organizationServive.getAllOrganizations());
    }

    @GetMapping("/getInfo/{organizationId}")
    public Response getInfoOfOneOrganization(@PathVariable("organizationId") Integer organizationId) {
        return organizationServive.getInfoOfOne(organizationId);
    }

    @GetMapping("/getInfo")
    @Secured("ORGANIZATION")
    public Response getInfoOfOrganization(@RequestAttribute User user) {
        return organizationServive.getInfo(user.getOrganization());
    }

    @PutMapping("/updateInfo")
    @Secured("ORGANIZATION")
    public Response updateInfoOfOneOrganization(@RequestAttribute User user,
            @RequestBody UpdateOrganizationRequest request) {
        return organizationServive.updateInfoOfOne(user.getOrganization().getUserId(), request);
    }
}
