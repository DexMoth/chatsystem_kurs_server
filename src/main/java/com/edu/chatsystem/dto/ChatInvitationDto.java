package com.edu.chatsystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class ChatInvitationDto {
    private Long chatId;
    private String chatName;
    private List<String> recipientEmails;
    private String inviterName;

    public ChatInvitationDto() {}
    public ChatInvitationDto(Long chatId, String chatName, List<String> recipientEmails, String inviterName) {
        this.chatId = chatId;
        this.chatName = chatName;
        this.recipientEmails = recipientEmails;
        this.inviterName = inviterName;
    }
    public Long getChatId() {
        return chatId;
    }
    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getChatName() {
        return chatName;
    }
    public void setChatName(String chatName) {
        this.chatName = chatName;
    }
    public List<String> getRecipientEmails() {
        return recipientEmails;
    }
    public void setRecipientEmails(List<String> recipientEmails) {
        this.recipientEmails = recipientEmails;
    }
    public String getInviterName() {
        return inviterName;
    }
    public void setInviterName(String inviterName) {
        this.inviterName = inviterName;
    }
}
