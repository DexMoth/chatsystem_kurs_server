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
    @Query("SELECT m FROM MessageEntity m " +
            "JOIN FETCH m.user u " +
            "JOIN m.chat c " +
            "WHERE c.id = :chatId " +
            "ORDER BY m.createdAt ASC") //сортировка по дате
    List<MessageEntity> findByChatId(@Param("chatId") Long chatId);
}