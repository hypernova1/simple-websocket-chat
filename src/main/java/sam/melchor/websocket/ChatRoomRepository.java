package sam.melchor.websocket;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ChatRoomRepository {
    public List<ChatRoom> getList() {
        List<ChatRoom> rooms = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ChatRoom room = new ChatRoom(UUID.randomUUID().toString(), i + "번째 방");
            rooms.add(room);
        }
        return rooms;
    }
}
