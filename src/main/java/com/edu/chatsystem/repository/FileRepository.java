package com.edu.chatsystem.repository;

import com.edu.chatsystem.model.ChatEntity;
import com.edu.chatsystem.model.FileEntity;
import com.edu.chatsystem.model.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    @Query("SELECT m FROM MessageEntity m WHERE m.id = :messageId")
    List<FileEntity> findByMessageId(@Param("messageId") Long messageId);
}