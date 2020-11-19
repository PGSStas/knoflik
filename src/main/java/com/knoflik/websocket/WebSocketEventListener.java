package com.knoflik.websocket;

import com.knoflik.entities.User;
import com.knoflik.services.RoomService;
import com.knoflik.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {
    @Autowired
    private WebsocketRoomController roomController;
    @Autowired
    private UserService userService;
    @Autowired
    private RoomService roomService;


    @EventListener
    public void handleSubscribe(final SessionConnectedEvent event)
            throws Exception {
        User user = userService.getLoggedUser();
        System.out.println("disconnection: " + user);
//        String id = userService.getLoggedUser().getCurrentRoomId();
//        roomController.updateRoomInfo(id);
    }

    @EventListener
    public void handleWebSocketDisconnectListener(
            final SessionDisconnectEvent event) throws Exception {
        User user = userService.getLoggedUser();
        System.out.println("disconnection: " + user);
//        String id = userService.getLoggedUser().getCurrentRoomId();
//        roomService.removeUserFromRoom(id);
//        roomController.updateRoomInfo(id);
    }

}
