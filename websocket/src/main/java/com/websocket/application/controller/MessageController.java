package com.websocket.application.controller;

import com.websocket.domain.model.Message;
import com.websocket.domain.model.Response;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MessageController {

    @MessageMapping("/hello")
    @SendTo("/topic/response")
    public Response sendMessage(Message message) throws Exception {
        return new Response("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
