package com.edu.chatsystem.repository;

import com.edu.chatsystem.model.ChatEntity;
import com.edu.chatsystem.model.MessageEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MessageRepository extends CrudRepository<MessageEntity, Long> {
}