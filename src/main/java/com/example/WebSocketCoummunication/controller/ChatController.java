package com.example.WebSocketCoummunication.controller;

import com.example.WebSocketCoummunication.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class ChatController {

    @MessageMapping("/hello")
    @SendTo("/topic/message")
    public Message greeting(Message message) {
        String content = HtmlUtils.htmlEscape(message.getName()) + ": " + HtmlUtils.htmlEscape(message.getContent());

        return new Message(message.getName(), content);
    }
}
