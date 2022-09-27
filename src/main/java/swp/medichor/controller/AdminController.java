package swp.medichor.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swp.medichor.model.response.Response;
import swp.medichor.service.AdminService;

@RestController
@RequestMapping("v1/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PutMapping("/verifyOrgAccount/{organizationId}")
    public Response verifyOrganizationAccount(@PathVariable("organizationId") Integer id) {
        return adminService.verifyOrganizationAccount(id);
    }

    @PutMapping("/rejectOrgAccount/{organizationId}")
    public Response rejectOrganizationAccount(@PathVariable("organizationId") Integer id) {
        return adminService.rejectOrganizationAccount(id);
    }
}
