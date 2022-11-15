package swp.medichor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import swp.medichor.model.User;
import swp.medichor.model.request.AnswerRequest;
import swp.medichor.model.request.QuestionRequest;
import swp.medichor.model.response.Response;
import swp.medichor.service.QuestionService;

@RestController
@RequestMapping("/v1/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/add/{campaignId}")
    @Secured("DONOR")
    public Response addQuestion(@RequestAttribute User user,
            @PathVariable("campaignId") Integer campaignId,
            @RequestBody QuestionRequest request) {
        return questionService.addQuestion(user, campaignId, request);
    }

    @PutMapping("/answer/{questionId}")
    @Secured("ORGANIZATION")
    public Response answerQuestion(@RequestAttribute User user,
            @PathVariable("questionId") Integer questionId,
            @RequestBody AnswerRequest request) {
        return questionService.answerQuestion(user, questionId, request);
    }

    @PutMapping("/refuse/{questionId}")
    @Secured("ORGANIZATION")
    public Response refuseQuestion(@RequestAttribute User user,
            @PathVariable("questionId") Integer questionId) {
        return questionService.refuseQuestion(user, questionId);
    }

    @GetMapping("/get-by-campaign/{campaignId}")
    public Response getAllQuestionsOfCampaign(@PathVariable("campaignId") Integer campaignId) {
        return questionService.getAllQuestionsOfCampaign(campaignId);
    }

    @GetMapping("/get-by-donor")
    @Secured("DONOR")
    public Response getAllQuestionsOfDonor(@RequestAttribute User user) {
        return questionService.getAllQuestionsOfDonor(user.getDonor().getUserId());
    }

    @GetMapping("/get-by-organization")
    @Secured("ORGANIZATION")
    public Response getAllQuestionsOfOrganization(@RequestAttribute User user) {
        return questionService.getAllQuestionsOfOrganization(user.getOrganization().getUserId());
    }
}
