package swp.medichor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swp.medichor.model.response.Response;

@RestController
@RequestMapping("/api/test")
public class DemoController {
    @GetMapping()
    public Response test() {
        return new Response(200, true, "This is for testing controller");
    }
}
