package com.example.WebSocket.controller;

import com.example.WebSocket.dto.request.MessageRequestDto;
import com.example.WebSocket.entity.ChatMessage;
import com.example.WebSocket.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class ChatController {


    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;

    @GetMapping()
    public ResponseEntity<List<ChatMessage>> getAllMessages(){
        List<ChatMessage> messages = chatMessageService.getAllMessages();

        return ResponseEntity.ok(messages);
    }

    @PostMapping("/send")
    public String sendTestMessage(@RequestBody MessageRequestDto messageRequestDto) {

        chatMessageService.save(messageRequestDto);

        messagingTemplate.convertAndSend("/topic/public", messageRequestDto);
        return "Message sent!";
    }
}
