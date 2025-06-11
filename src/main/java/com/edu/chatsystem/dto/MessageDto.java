package com.edu.chatsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

public class MessageDto {
    private Long id;
    private Long chatId;
    private Long userId;
    private String text;
    private boolean isFavorite;

    public MessageDto() {}

    ;
    public void setId(Long id) {
        this.id = id;
    }

    ;
    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
    public Long getId() {
        return id;
    }

    public Long getChatId() {
        return chatId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }

    public boolean isFavorite() {
        return isFavorite;
    }
}
