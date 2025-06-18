package com.edu.chatsystem.repository;

import com.edu.chatsystem.model.ChatEntity;
import com.edu.chatsystem.model.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    @Query("SELECT DISTINCT m FROM MessageEntity m " +
            "LEFT JOIN FETCH m.user u " +
            "LEFT JOIN FETCH m.attachments " +
            "WHERE m.chat.id = :chatId " +
            "ORDER BY m.createdAt ASC")
    List<MessageEntity> findByChatId(@Param("chatId") Long chatId);

    @Query("SELECT DISTINCT m FROM MessageEntity m " +
            "LEFT JOIN FETCH m.attachments")  // Добавлен новый метод
    List<MessageEntity> findAllWithAttachments();
}