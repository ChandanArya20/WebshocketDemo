package in.ineuron.scheduler;

import in.ineuron.dto.MessageRequest;
import in.ineuron.dto.MessageResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    private final SimpMessagingTemplate messagingTemplate;

    public MessageController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping("/send")
    public ResponseEntity<MessageResponse> sendMessageHandler( @RequestBody MessageRequest msgReq) {
        MessageResponse msgRes = new MessageResponse();
        BeanUtils.copyProperties(msgReq, msgRes);

        // Send the message to "/topic/message" destination
        messagingTemplate.convertAndSend("/topic/message", msgRes);

        return ResponseEntity.ok(msgRes);
    }
}
