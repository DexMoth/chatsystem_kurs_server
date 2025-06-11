package com.edu.chatsystem.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "message")
@Data
public class MessageEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id", nullable = false)
    private ChatEntity chat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false, length = 4096)
    private String text;

    //@Column(nullable = false)
    //private LocalDateTime createdAt = LocalDateTime.now();

    private boolean isFavorite;

    public MessageEntity() {
    }

    public MessageEntity(ChatEntity chat, UserEntity user, String text) {
        this.chat = chat;
        this.user = user;
        this.text = text;
    }

    public ChatEntity getChat() {
        return chat;
    }

    public void setChat(ChatEntity chat) {
        this.chat = chat;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    /*public LocalDateTime getCreatedAt() {
        return createdAt;

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    }*/
    public boolean getIsFavorite() {
        return isFavorite;
    }
    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        final MessageEntity other = (MessageEntity) obj;
        return Objects.equals(other.getId(), id)
                && Objects.equals(other.getChat().getId(), chat.getId())
                && Objects.equals(other.getUser().getId(), user.getId())
                && Objects.equals(other.getText(), text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chat != null ? chat.getId() : null,
                user != null ? user.getId() : null, text);
    }
}