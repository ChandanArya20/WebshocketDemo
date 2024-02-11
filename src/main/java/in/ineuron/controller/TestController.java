package in.ineuron.controller;

import in.ineuron.models.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @MessageMapping("/hello")
    @SendTo("/topic/message")
    public Message sendMessage(@Payload Message msg){
        return msg;
    }

    @MessageMapping("/message")
    @SendTo("/topic/message")
    public String sendContinuousMessage(@Payload String msg){
        return "Ram";
    }



}
