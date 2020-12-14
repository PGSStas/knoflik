package com.knoflik.config;

import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

public class WebSocketSecurityConfig extends
        AbstractSecurityWebSocketMessageBrokerConfigurer {
    @Override
    protected void configureInbound(
            final MessageSecurityMetadataSourceRegistry messages) {
        messages
                .simpDestMatchers("/secured/**").authenticated()
                .anyMessage().authenticated();
    }

    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }
}
