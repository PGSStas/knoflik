package com.knoflik.rest;

import com.knoflik.entities.Room;
import com.knoflik.entities.User;
import com.knoflik.questions.Question;
import com.knoflik.services.RoomService;
import com.knoflik.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/rooms")
public class QnAController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private UserService userService;

    @Autowired
    private SimpMessagingTemplate template;

    @GetMapping("/{id}/answer")
    private void recieveAnswer(@PathVariable final String id) {
        User user = userService.getLoggedUser();
        this.template.convertAndSend(
                "/secured/topic/room/" + id + "/answer",
                "Stop, answering " + user.getUsername());
    }

    @GetMapping("/{id}/answer.false")
    private void falseAnswer(@PathVariable final String id) {
        this.template.convertAndSend(
                "/secured/topic/room/" + id + "/answer", "false");
    }


    @GetMapping("/{id}/answer.true")
    private void trueAnswer(@PathVariable final String id) {
        this.template.convertAndSend(
                "/secured/topic/room/" + id + "/answer", "true");

        this.template.convertAndSend(
                "/secured/topic/room/" + id + "/admin", "Change button");
    }

    @GetMapping("/{id}/nextQuestion")
    public void nextQuestion(@PathVariable final String id) {
        Room room = roomService.getRoomById(id);
        Question question = room.getQuestionStat().getNextQuestion();

        this.template.convertAndSend("/secured/topic/room/" + id + "/next",
                question.getQuestion());
        this.template.convertAndSend("/secured/topic/room/" + id + "/admin",
                question.getAnswer());
    }
}
