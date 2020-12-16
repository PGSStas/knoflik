package com.knoflik.rest;

import com.knoflik.entities.QuestionStat;
import com.knoflik.entities.User;
import com.knoflik.questions.Answer;
import com.knoflik.repositories.questions.PackRepository;
import com.knoflik.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("api/rooms")
public class QnAController {
    @Autowired
    private PackRepository packRepository;
    @Autowired
    private UserService userService;

    private SimpMessagingTemplate template;

    private static Set<QuestionStat> questionStatSet;

    @Autowired
    public QnAController(final SimpMessagingTemplate template) {
        this.template = template;
    }

    @GetMapping("/{id}/answer")
    private void recieveAnswer(
            @ModelAttribute("answer") final Answer answer, final Model model)
            throws InterruptedException {
        //todo: проверка правильности вопроса
        User user = userService.getLoggedUser();
        String id = user.getCurrentRoom().getId();
        if (true) {
            this.template.convertAndSend(
                    "/secured/topic/room/" + id + "/answer", "true");
            TimeUnit.SECONDS.sleep(3);

        } else {
            this.template.convertAndSend(
                    "/secured/topic/room/" + id + "/answer", "false");
        }

    }

    public String sendCorrectAnswer(final String id) {
        this.template.convertAndSend("/secured/topic/room/" + id, questionStatSet.);
    }
}
