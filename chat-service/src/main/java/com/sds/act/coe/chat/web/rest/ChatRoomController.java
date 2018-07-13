package com.sds.act.coe.chat.web.rest;

import com.sds.act.coe.chat.repository.ChatRoomRepository;
import com.sds.act.coe.chat.vo.ChatRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatRoomController {

    private final ChatRoomRepository repository;
    private final AtomicInteger seq = new AtomicInteger(0);

    @Autowired
    public ChatRoomController(ChatRoomRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/rooms")
    public List<ChatRoom> rooms() {
        return repository.getChatRooms();
    }

    @GetMapping("/rooms/{id}")
    public ChatRoom room(@PathVariable String id) {
        return repository.getChatRoom(id);
    }
}