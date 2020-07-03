package sam.melchor.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatRoomRepository chatRoomRepository;

    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> toRooms() {
        return chatRoomRepository.getList();
    }

    @GetMapping("/rooms/{id}")
    public String toRoom() {
        return "/chatRoom";
    }

    @MessageMapping("/{roomId}")
    @SendTo("/subscribe/{roomId}")
    public Message greeting(@DestinationVariable String roomId, Message message) {
        return message;
    }



}
