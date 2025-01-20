package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.RoomChat;
import com.example.demo.repository.RoomChatRepo;

import jakarta.transaction.Transactional;

public interface RoomChatService {
	void delete(int id);
	void create(RoomChat roomChat);
	List<RoomChat> getAll();
	RoomChat getById(int id);
}


@Service
@Transactional
class RoomChatServiceImpl implements RoomChatService {
	
	@Autowired
	private RoomChatRepo chatRepo;
	

	@Override
	public void delete(int id) {
		chatRepo.deleteById(id);;
	}

	@Override
	public void create(RoomChat roomChat) {
		chatRepo.save(roomChat);
	}

	@Override
	public List<RoomChat> getAll() {
		return chatRepo.findAll();
	}

	@Override
	public RoomChat getById(int id) {
		return chatRepo.findById(id).orElse(null);
	}
	
}
