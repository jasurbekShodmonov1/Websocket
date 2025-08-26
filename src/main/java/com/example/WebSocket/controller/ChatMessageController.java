package com.example.WebSocket.controller;


import com.example.WebSocket.dto.request.MessageRequestDto;
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
    public MessageRequestDto sendMessage(MessageRequestDto messageRequestDto) {
        System.out.println("Received  Websocket message:" + messageRequestDto.content());
        chatMessageService.save(messageRequestDto);

        return messageRequestDto;
    }
}
