package com.edu.chatsystem.controller;

import com.edu.chatsystem.configuration.Constants;
import com.edu.chatsystem.dto.ChatInvitationDto;
import com.edu.chatsystem.service.ChatService;
import com.edu.chatsystem.service.EmailService;
import com.edu.chatsystem.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.API_URL + "/notification")
public class NotificationController {
    private final EmailService emailService;
    private final ChatService chatService;
    private final UserService userService;

    public NotificationController(EmailService emailService, ChatService chatService, UserService userService) {
        this.emailService = emailService;
        this.chatService = chatService;
        this.userService = userService;
    }
    @PostMapping("/chat-invite")
    public ResponseEntity<?> sendChatInvitation(
            @RequestBody ChatInvitationDto request) {
        String chatLink = "http://localhost:5173/join-chat/" + request.getChatId();

        request.getRecipientEmails().forEach(email -> {
            emailService.sendEmail(
                    email,
                    "Приглашение в чат: " + request.getChatName(),
                    request.getInviterName() + " приглашает вас в чат. Перейдите по ссылке: " + chatLink
            );
        });

        return ResponseEntity.ok().build();
    }

    private String buildInvitationEmailContent(String inviterName, String chatLink) {
        return "<html><body>" +
                "<h2>Вас пригласили в чат</h2>" +
                "<p>" + inviterName + " приглашает вас присоединиться к чату.</p>" +
                "<a href=\"" + chatLink + "\">Перейти в чат</a>" +
                "</body></html>";
    }
}
