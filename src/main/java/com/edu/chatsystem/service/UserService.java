package com.edu.chatsystem.service;

import com.edu.chatsystem.error.NotFoundException;
import com.edu.chatsystem.model.ChatEntity;
import com.edu.chatsystem.model.UserEntity;
import com.edu.chatsystem.repository.ChatRepository;
import com.edu.chatsystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    private final UserRepository repository;
    private final ChatRepository chatRepository;
    public UserService(UserRepository repository, ChatRepository chatRepository) {
        this.repository = repository;
        this.chatRepository = chatRepository;
    }
    @Transactional
    public List<UserEntity> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).toList();
    }
    @Transactional
    public UserEntity get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(UserEntity.class, id));
    }

    @Transactional
    public UserEntity create(UserEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity is null");
        }
        checkLogin(entity.getLogin());
        return repository.save(entity);
    }

    @Transactional
    public UserEntity update(Long id,  UserEntity entity) {
        UserEntity user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        user.setLogin(entity.getLogin());
        user.setPassword(entity.getPassword());
        user.setName(entity.getName());
        user.setPhone(entity.getPhone());
        user.setAvatar(entity.getAvatar());
        return repository.save(user);
    }

    @Transactional
    public UserEntity delete(Long id) {
        final UserEntity existsEntity = get(id);
        repository.delete(existsEntity);
        return existsEntity;
    }

    private void checkLogin(String login) {
        if (repository.findByLoginIgnoreCase(login).isPresent()) {
            throw new IllegalArgumentException(
                    String.format("Type with login %s is already exists", login));
        }
    }
}
