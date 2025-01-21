package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.RoomChat;
import com.example.demo.service.RoomChatService;

@RestController
@RequestMapping("/api/roomchat")
@CrossOrigin(origins = "*")
public class RoomChatController {
	
	@Autowired
	private RoomChatService roomChatService;
	
	@GetMapping("/all")
    public List<RoomChat> getAllRooms() {
        return roomChatService.getAll();
    }
	
	@GetMapping("/{id}")
    public RoomChat getRoomById(@PathVariable int id) {
        return roomChatService.getById(id);
    }
	
	@PostMapping("")
	public RoomChat create(@RequestBody RoomChat roomChat) {
		roomChatService.create(roomChat);
		return roomChat;
	}

}
