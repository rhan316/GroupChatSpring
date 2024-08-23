package com.example.WebSocketCoummunication.controller;

import com.example.WebSocketCoummunication.model.Message;
import com.example.WebSocketCoummunication.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

@Controller
public class ChatController {

    private final FormService formService;

    @Autowired
    public ChatController(FormService formService) {
        this.formService = formService;
    }

    @GetMapping("/")
    public String form() {
        return "form";
    }

    @GetMapping("/home")
    public String home() {
        return "index";
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam String nickname, @RequestParam String password) {
        if (formService.isValidUser(nickname, password)) {
            return new ModelAndView("redirect:/home");
        } else {
            ModelAndView mav = new ModelAndView("form");
            mav.addObject("error", "Invalid nickname or password!");
            return mav;
        }
    }

    @MessageMapping("/hello")
    @SendTo("/topic/message")
    public Message greeting(Message message) {
        String username = HtmlUtils.htmlEscape(message.getUser().getUsername());
        String content = HtmlUtils.htmlEscape(message.getContent());

        return new Message(message.getUser(), username + ": " + content, message.getCreatedAt());
    }
}
