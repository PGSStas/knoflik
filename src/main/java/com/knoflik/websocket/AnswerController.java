package com.knoflik.websocket;

import com.knoflik.questions.Answer;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class AnswerController {

    @MessageMapping("/room/{id}.sendAnswer")
    public void sendAnswerToAdmin(@DestinationVariable final String id,
                                    @Payload final Answer message,
                                    final SimpMessagingTemplate
                                            messagingTemplate) {
        messagingTemplate.convertAndSend(
                "/secured/topic/room/" + id + "/admin",
                message.getMessage());
    }
}
