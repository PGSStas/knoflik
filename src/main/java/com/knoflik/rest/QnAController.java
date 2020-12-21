package com.knoflik.rest;

import com.knoflik.entities.QuestionStat;
import com.knoflik.entities.User;
import com.knoflik.questions.Question;
import com.knoflik.repositories.room.QuestionStatRepository;
import com.knoflik.services.RoomService;
import com.knoflik.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/rooms")
public class QnAController {
    @Autowired
    private QuestionStatRepository questionStatRepository;
    @Autowired
    private RoomService roomService;
    @Autowired
    private UserService userService;

    @Autowired
    private SimpMessagingTemplate template;

    @PostMapping("/{id}/answer")
    private void recieveAnswer(@PathVariable final String id) {
        User user = userService.getLoggedUser();
        this.template.convertAndSend(
                "/secured/topic/room/" + id + "/answer",
                "Stop, answering " + user.getUsername());
    }

    @PostMapping("/{id}/answer.false")
    private void falseAnswer(@PathVariable final String id) {
        this.template.convertAndSend(
                "/secured/topic/room/" + id + "/answer", "false");
    }


    @PostMapping("/{id}/answer.true")
    private void trueAnswer(@PathVariable final String id) {
        this.template.convertAndSend(
                "/secured/topic/room/" + id + "/answer", "true");
    }

    @PostMapping("/{id}/nextQuestion")
    public void nextQuestion(@PathVariable final String id) {
        QuestionStat questionStat =
                roomService.getRoomById(id).getQuestionStat();
        Question question = questionStat.getNextQuestion();
        questionStatRepository.save(questionStat);

        this.template.convertAndSend("/secured/topic/room/" + id + "/next",
                question.getQuestion());
        this.template.convertAndSend("/secured/topic/room/" + id + "/admin",
                question.getAnswer());
    }
}
