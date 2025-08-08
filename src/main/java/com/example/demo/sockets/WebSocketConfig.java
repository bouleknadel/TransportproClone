package com.example.demo.sockets;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Autowired
    private SocketCamionUpdatesHandler socketCamionUpdatesHandler;
    
    @Autowired
    private ChatWebSocketHandler chatWebSocketHandler;

    @Value("${app.client.url:http://localhost:3000}")
    private String clientUrl;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new SocketUpdatesHandler(), "/ws/users")
                .setAllowedOrigins(clientUrl);
        registry.addHandler(socketCamionUpdatesHandler, "/ws/camions")
                .setAllowedOrigins("*");
        registry.addHandler(chatWebSocketHandler, "/ws/chat")
                .setAllowedOrigins("*");
        registry.addHandler(chatWebSocketHandler, "/ws/chat/{reservationId}")
                .setAllowedOrigins("*");
    }
}