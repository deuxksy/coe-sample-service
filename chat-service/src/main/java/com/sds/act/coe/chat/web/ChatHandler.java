package com.sds.act.coe.chat.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sds.act.coe.chat.repository.ChatRoomRepository;
import com.sds.act.coe.chat.vo.ChatMessage;
import com.sds.act.coe.chat.vo.ChatRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class ChatHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final ChatRoomRepository repository;

    @Autowired
    public ChatHandler(ObjectMapper objectMapper, ChatRoomRepository chatRoomRepository) {
        this.objectMapper = objectMapper;
        this.repository = chatRoomRepository;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("handleTextMessage");
        String payload = message.getPayload();

        System.out.println(payload);
        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);

        System.out.println(chatMessage);
        ChatRoom chatRoom = repository.getChatRoom(chatMessage.getChatRoomId());

        System.out.println(chatRoom);
        chatRoom.handleMessage(session, chatMessage, objectMapper);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("afterConnectionClosed");
        repository.remove(session);
    }
}