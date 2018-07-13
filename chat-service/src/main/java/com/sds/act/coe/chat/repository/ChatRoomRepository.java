package com.sds.act.coe.chat.repository;

import com.sds.act.coe.chat.vo.ChatRoom;
import org.springframework.stereotype.Repository;
import org.springframework.web.socket.WebSocketSession;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class ChatRoomRepository {
    private final Map<String, ChatRoom> chatRoomMap;

    public ChatRoomRepository() {
        chatRoomMap = Collections.unmodifiableMap(
                Stream.of(ChatRoom.create("1번방"), ChatRoom.create("2번방"), ChatRoom.create("3번방"))
                        .collect(Collectors.toMap(ChatRoom::getId, Function.identity())));

//        chatRooms = Collections.unmodifiableCollection(chatRoomMap.values());
    }

    public ChatRoom getChatRoom(String id) {
        return chatRoomMap.get(id);
    }

    public List<ChatRoom> getChatRooms() {
        return new ArrayList<>(chatRoomMap.values());
    }

    public void remove(WebSocketSession session) {

    }
}
