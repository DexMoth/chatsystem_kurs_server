package com.edu.chatsystem.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

import java.util.Objects;

@Entity
@Table(name = "chat")
@Data
public class ChatEntity extends BaseEntity {
    private String name;
       /* @ManyToMany(mappedBy = "chats")
        private Set<UserEntity> members = new HashSet<>();*/
    @ManyToMany
    @JoinTable(
           name = "user_chat",
           joinColumns = @JoinColumn(name = "chat_id"),
           inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<UserEntity> members = new HashSet<>();
    public ChatEntity() {}
    public ChatEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserEntity> getMembers() {
        return members;
    }

    public void addMember(UserEntity user) {
        members.add(user);
    }

    public void removeMember(UserEntity user) {
        members.remove(user);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        final ChatEntity other = (ChatEntity) obj;
        return Objects.equals(other.getId(), id)
                && Objects.equals(other.getName(), name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}