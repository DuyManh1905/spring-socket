package com.example.demo.model;

import lombok.Data;

@Data
public class ChatMessage {
	private String roomName;
    private String sender;
    private String content;
}
