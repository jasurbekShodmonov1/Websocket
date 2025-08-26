package com.example.WebSocket.service;


import com.example.WebSocket.dto.request.MessageRequestDto;
import com.example.WebSocket.dto.response.MessageResponseDto;
import com.example.WebSocket.entity.ChatMessage;
import com.example.WebSocket.repository.ChatMessageRepository;
import com.example.WebSocket.security.HashUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    public ChatMessage saveMessage(ChatMessage chatMessage){
        return chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> getAllMessages(){
        return chatMessageRepository.findAll();
    }

//    public MessageResponseDto save(MessageRequestDto messageRequestDto){
//        ChatMessage message = new ChatMessage();
//
//        message.setSender(messageRequestDto.sender());
//        message.setContent(messageRequestDto.content());
//
//        ChatMessage saved = chatMessageRepository.save(message);
//
//        MessageResponseDto messageResponseDto = new MessageResponseDto(saved);
//
//        return messageResponseDto;
//
//
//    }

    public ChatMessage save(MessageRequestDto messageRequestDto){
        ChatMessage message = ChatMessage.builder()
                .sender(messageRequestDto.sender())
                .content(HashUtil.sha256(messageRequestDto.content()))
                .timestamp(LocalDateTime.now())
                .build();

        return chatMessageRepository.save(message);
    }

}
