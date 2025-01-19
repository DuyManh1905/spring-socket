package com.example.demo.controller;

import com.example.demo.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final List<ChatMessage> messageHistory = new ArrayList<>();

    @MessageMapping("/sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage message) {
        messageHistory.add(message); // Lưu lại lịch sử chat
        return message;
    }

    @GetMapping("/history")
    public List<ChatMessage> getChatHistory() {
        return messageHistory;
    }
}
