package com.edu.chatsystem.service;

import com.edu.chatsystem.error.NotFoundException;
import com.edu.chatsystem.model.MessageEntity;
import com.edu.chatsystem.repository.MessageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class MessageService {
    private final MessageRepository repository;
    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }
    @Transactional
    public List<MessageEntity> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).toList();
    }
    @Transactional
    public MessageEntity get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageEntity.class, id));
    }
    @Transactional
    public MessageEntity create(MessageEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity is null");
        }
        return repository.save(entity);
    }

    @Transactional
    public MessageEntity update(Long id, MessageEntity ent) {
        final MessageEntity el = get(id);
        el.setText(ent.getText());
        return repository.save(el);
    }

    @Transactional
    public MessageEntity delete(Long id) {
        final MessageEntity el = get(id);
        repository.delete(el);
        return el;
    }
}
