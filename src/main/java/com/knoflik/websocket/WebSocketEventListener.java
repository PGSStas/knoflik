package com.knoflik.websocket;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public final class WebSocketEventListener {

    @EventListener
    public void handleWebSocketConnectListener(
            final SessionConnectedEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        System.out.println("connection: " + sha.toString());
    }

    @EventListener
    public void handleWebSocketDisconnectListener(
            final SessionDisconnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        System.out.println("disconnection: " + sha.toString());
    }

}
