package swp.medichor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.medichor.model.Campaign;
import swp.medichor.model.Donor;
import swp.medichor.model.Question;
import swp.medichor.model.User;
import swp.medichor.model.request.AnswerRequest;
import swp.medichor.model.request.QuestionRequest;
import swp.medichor.model.response.QuestionResponse;
import swp.medichor.model.response.Response;
import swp.medichor.repository.CampaignRepository;
import swp.medichor.repository.DonorRepository;
import swp.medichor.repository.QuestionRepository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private CampaignRepository campaignRepository;
    @Autowired
    private DonorRepository donorRepository;

    public Response addQuestion(User user, Integer campaignId, QuestionRequest request) {
        Optional<Campaign> isExistCampaign = campaignRepository.findById(campaignId);
        if (isExistCampaign.isEmpty()) {
            return new Response(400, false, "ID không tồn tại");
        }
        Campaign campaign = isExistCampaign.get();
        Question question = Question.builder()
                .donor(user.getDonor())
                .campaign(campaign)
                .askTime(new Timestamp(System.currentTimeMillis()))
                .question(request.getQuestion())
                .answer(null)
                .build();
        questionRepository.save(question);
        return new Response(200, true, "Đặt câu hỏi thành công");
    }

    @Transactional
    public Response answerQuestion(User user, Integer questionId, AnswerRequest request) {
        Optional<Question> isExistQuestion = questionRepository.findById(questionId);
        if (isExistQuestion.isEmpty())
            return new Response(400, false, "ID không tồn tại");
        Question question = isExistQuestion.get();
        if (!user.getId().equals(question.getCampaign().getOrganization().getUserId()))
            return new Response(403, false, "Bạn không có quyền trả lời câu hỏi từ chiến dịch của tổ chức khác");
        if (request.getAnswer() == null || request.getAnswer().equals(""))
            return new Response(400, false, "Câu trả lời không được bỏ trống");
        question.setAnswer(request.getAnswer());
        question.setStatus(true);
        return new Response(200, true, "Trả lời thành công");
    }

    @Transactional
    public Response refuseQuestion(User user, Integer questionId) {
        Optional<Question> isExistQuestion = questionRepository.findById(questionId);
        if (isExistQuestion.isEmpty())
            return new Response(400, false, "ID không tồn tại");
        Question question = isExistQuestion.get();
        if (!user.getId().equals(question.getCampaign().getOrganization().getUserId()))
            return new Response(403, false, "Bạn không có quyền từ chối câu hỏi từ chiến dịch của tổ chức khác");
        question.setAnswer("REFUSED");
        question.setStatus(false);
        return new Response(200, true, "Từ chối trả lời thành công");
    }


    public Response getAllQuestionsOfCampaign(Integer campaignId) {
        Optional<Campaign> isExistCampaign = campaignRepository.findById(campaignId);
        if (isExistCampaign.isEmpty()) {
            return new Response(400, false, "ID không tồn tại");
        }
        List<Question> listOfQuestions = questionRepository.findByCampaignId(campaignId);
        List<QuestionResponse> listOfQuestionResponse = new ArrayList<>();
        for (Question question : listOfQuestions) {
            listOfQuestionResponse.add(new QuestionResponse(
                    question.getId(),
                    question.getDonor().getUserId(),
                    question.getDonor().getName(),
                    question.getCampaign().getId(),
                    question.getCampaign().getName(),
                    question.getAskTime(),
                    question.getQuestion(),
                    question.getAnswer(),
                    question.getStatus()
            ));
        }
        return new Response(200, true, listOfQuestionResponse);
    }

    public Response getAllQuestionsOfDonor(Integer donorId) {
        List<Question> listOfQuestions = questionRepository.findByDonorId(donorId);
        List<QuestionResponse> listOfQuestionResponse = new ArrayList<>();
        for (Question question : listOfQuestions) {
            listOfQuestionResponse.add(new QuestionResponse(
                    question.getId(),
                    question.getDonor().getUserId(),
                    question.getDonor().getName(),
                    question.getCampaign().getId(),
                    question.getCampaign().getName(),
                    question.getAskTime(),
                    question.getQuestion(),
                    question.getAnswer(),
                    question.getStatus()
            ));
        }
        return new Response(200, true, listOfQuestionResponse);
    }

    public Response getAllQuestionsOfOrganization(Integer organizationId) {
        List<Question> listOfQuestions = questionRepository.findByOrganizationId(organizationId);
        List<QuestionResponse> listOfQuestionResponse = new ArrayList<>();
        for (Question question : listOfQuestions) {
            listOfQuestionResponse.add(new QuestionResponse(
                    question.getId(),
                    question.getDonor().getUserId(),
                    question.getDonor().getName(),
                    question.getCampaign().getId(),
                    question.getCampaign().getName(),
                    question.getAskTime(),
                    question.getQuestion(),
                    question.getAnswer(),
                    question.getStatus()
            ));
        }
        return new Response(200, true, listOfQuestionResponse);
    }

}
