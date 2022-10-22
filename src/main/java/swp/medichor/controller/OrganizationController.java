package swp.medichor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swp.medichor.model.User;
import swp.medichor.model.request.AnswerRequest;
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

    @GetMapping
    public Response getAllOrganization() {
        return new Response(200, true, organizationServive.getAllOrganizations());
    }

    @GetMapping("/getInfo/{organizationId}")
    public Response getInfoOfOneOrganization(@PathVariable("organizationId") Integer organizationId) {
        return organizationServive.getInfoOfOne(organizationId);
    }

    @GetMapping("/getInfo")
    public Response getInfoOfOrganization(@RequestAttribute User user) {
        return organizationServive.getInfo(user.getOrganization());
    }
    @PutMapping("/updateInfo")
    public Response updateInfoOfOneOrganization(@RequestAttribute User user,
                                                @RequestBody UpdateOrganizationRequest request) {
        return organizationServive.updateInfoOfOne(user.getOrganization().getUserId(), request);
    }

    @PutMapping("/answer-question/{questionId}")
    public Response answerQuestion(@RequestAttribute User user,
                                   @PathVariable("questionId") Integer questionId,
                                   @RequestBody AnswerRequest request) {
        return organizationServive.answerQuestion(user, questionId, request);
    }
}
