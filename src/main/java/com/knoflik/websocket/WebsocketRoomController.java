package com.knoflik.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knoflik.services.RoomService;
import com.knoflik.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketRoomController {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SimpMessagingTemplate template;

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;

    WebsocketRoomController(final SimpMessagingTemplate simpMessagingTemplate) {
        this.template = simpMessagingTemplate;
    }

    public void updateRoomInfo(final String id) throws Exception {
        this.template.convertAndSend("/topic/room/" + id,
                objectMapper.writeValueAsString(
                        roomService.getAllUsersFromRoom(id)));
    }
}
