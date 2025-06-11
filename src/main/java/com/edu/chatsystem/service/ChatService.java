package com.edu.chatsystem.service;

import com.edu.chatsystem.error.NotFoundException;
import com.edu.chatsystem.model.ChatEntity;
import com.edu.chatsystem.model.UserEntity;
import com.edu.chatsystem.repository.ChatRepository;
import com.edu.chatsystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class ChatService {
    private final ChatRepository repository;
    private final UserRepository repositoryUser;
    public ChatService(ChatRepository repository, UserRepository repositoryUser) {
        this.repository = repository;
        this.repositoryUser = repositoryUser;
    }

    @Transactional
    public List<ChatEntity> getAllbyUser(Long userId) {
        return StreamSupport.stream(repository.findByUserId(userId).spliterator(), false).toList();
    }
    @Transactional
    public List<ChatEntity> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).toList();
    }
    @Transactional
    public ChatEntity get(Long id) {
        return repository.findByIdWithMembers(id)
                .orElseThrow(() -> new NotFoundException(ChatEntity.class, id));
    }

    @Transactional
    public ChatEntity addUser(Long chatId, Long userId) {
        ChatEntity chat = get(chatId);
        UserEntity user = repositoryUser.findById(userId)
                .orElseThrow(() -> new NotFoundException(UserEntity.class, userId));
        chat.addMember(user);
        return repository.save(chat);
    }

    @Transactional
    public ChatEntity create(ChatEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity is null");
        }
        return repository.save(entity);
    }

    @Transactional
    public ChatEntity update(Long id, ChatEntity ent) {
        final ChatEntity el = get(id);
        el.setName(ent.getName());
        return repository.save(el);
    }

    @Transactional
    public ChatEntity delete(Long id) {
        final ChatEntity el = get(id);
        repository.delete(el);
        return el;
    }
}
