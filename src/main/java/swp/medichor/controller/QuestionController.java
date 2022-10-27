package swp.medichor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
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
    public Response addQuestion(@RequestAttribute User user,
                                @PathVariable("campaignId") Integer campaignId,
                                @RequestBody QuestionRequest request) {
        return questionService.addQuestion(user, campaignId, request);
    }

    @PutMapping("/answer/{questionId}")
    public Response answerQuestion(@RequestAttribute User user,
                                   @PathVariable("questionId") Integer questionId,
                                   @RequestBody AnswerRequest request) {
        return questionService.answerQuestion(user, questionId, request);
    }

    @GetMapping("/get-by-campaign/{campaignId}")
    public Response getAllQuestionsOfCampaign(@PathVariable("campaignId") Integer campaignId) {
        return questionService.getAllQuestionsOfCampaign(campaignId);
    }

    @GetMapping("/get-by-donor")
    public Response getAllQuestionsOfDonor(@RequestAttribute User user) {
        return questionService.getAllQuestionsOfDonor(user.getDonor().getUserId());
    }
}
