package com.example.WebSocketCoummunication.controller;

import com.example.WebSocketCoummunication.model.*;
import com.example.WebSocketCoummunication.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import java.time.LocalDate;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
public class ChatController {

    private final FormService formService;
    private static final Logger logger = Logger.getLogger(ChatController.class.getName());

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

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register/new")
    public ModelAndView registerNewUser(@RequestParam String nickname,
                                        @RequestParam String password,
                                        @RequestParam String email,
                                        @RequestParam String phone,
                                        @RequestParam String country,
                                        @RequestParam String gender,
                                        @RequestParam String age
                                        ) {
        var userInfo = new UserInfo(
                nickname,
                password,
                email,
                phone,
                Country.valueOf(country.toUpperCase()),
                Gender.valueOf(gender.toUpperCase()),
                age
        );

        logger.info("nickname: " + nickname);
        logger.info("password: " + password);
        logger.info("email: " + email);
        logger.info("phone: " + phone);
        logger.info("country: " + country);
        logger.info("gender: " + gender);

       Optional<User> checkUser = formService.addNewUser(nickname, password);
       Optional<UserInfo> userInfoCheck = formService.saveUserDetails(userInfo);

       return new ModelAndView("redirect:/home");
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
