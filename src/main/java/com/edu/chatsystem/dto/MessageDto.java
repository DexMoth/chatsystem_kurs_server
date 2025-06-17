package com.edu.chatsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public class MessageDto {
    private Long id;
    private Long chatId;
    private Long userId;
    private String text;
    private boolean isFavorite;
    private LocalDateTime createdAt;
    private List<FileDto> attachments;

    public MessageDto() {}

    public MessageDto(Long chatId, Long userId, String text, List<FileDto> attachments)
    {
        this.chatId = chatId;
        this.userId = userId;
        this.text = text;
        this.isFavorite = false;
        this.attachments = attachments;
    }
    public MessageDto(Long chatId, Long userId, String text, LocalDateTime createdAt)
    {
        this.chatId = chatId;
        this.userId = userId;
        this.text = text;
        this.isFavorite = false;
        this.createdAt = createdAt;
    }

    public MessageDto(Long id, Long chatId, Long userId, String text)
    {
        this.id = id;
        this.chatId = chatId;
        this.userId = userId;
        this.text = text;
        this.isFavorite = false;
    }

    public MessageDto(Long id, Long chatId, Long userId, String text, boolean isFavorite)
    {
       this.id = id;
       this.chatId = chatId;
       this.userId = userId;
       this.text = text;
       this.isFavorite = isFavorite;
    }

    public MessageDto(Long id, Long chatId, Long userId, String text, boolean isFavorite, LocalDateTime createdAt)
    {
        this.id = id;
        this.chatId = chatId;
        this.userId = userId;
        this.text = text;
        this.isFavorite = isFavorite;
        this.createdAt = createdAt;
    }

    public MessageDto(Long id, Long chatId, Long userId, String text, boolean isFavorite, LocalDateTime createdAt, List<FileDto> attachments)
    {
        this.id = id;
        this.chatId = chatId;
        this.userId = userId;
        this.text = text;
        this.isFavorite = isFavorite;
        this.createdAt = createdAt;
        this.attachments = attachments;
    }

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

    public void setFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }
    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}
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
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setAttachments(List<FileDto> attachments) {
        this.attachments = attachments;
    }
    public List<FileDto> getAttachments() {
        return attachments;
    }

    public void addAttachment(FileDto attachment) {
        attachments.add(attachment);
    }
}
