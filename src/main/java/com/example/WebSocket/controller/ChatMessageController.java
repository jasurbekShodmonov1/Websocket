package com.example.WebSocket.controller;


import com.example.WebSocket.entity.ChatMessage;
import com.example.WebSocket.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {

    private final ChatMessageService chatMessageService;


    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage message) {
        System.out.println("Received  Websocket message:" + message.getContent());
        return chatMessageService.saveMessage(message); // store in DB + broadcast
    }
}
