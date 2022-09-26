package swp.medichor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class DemoController {
    @GetMapping()
    public String test() {
        return "Hello wolrd";
    }
}
