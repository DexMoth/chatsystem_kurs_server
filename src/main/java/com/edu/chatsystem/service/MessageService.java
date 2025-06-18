package com.edu.chatsystem.service;

import com.edu.chatsystem.error.NotFoundException;
import com.edu.chatsystem.model.FileEntity;
import com.edu.chatsystem.model.MessageEntity;
import com.edu.chatsystem.repository.FileRepository;
import com.edu.chatsystem.repository.MessageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class MessageService {
    private final MessageRepository repository;
    private final FileRepository fileRepository;
    public MessageService(MessageRepository repository, FileRepository fileRepository) {
        this.repository = repository;
        this.fileRepository = fileRepository;
    }
    @Transactional
    public List<MessageEntity> getAll() {
        return StreamSupport.stream(repository.findAllWithAttachments().spliterator(), false).toList();
    }

    @Transactional
    public List<MessageEntity> getAllbyChat(Long chatId) {
        return repository.findByChatId(chatId);
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
        entity.setCreatedAt(LocalDateTime.now());
        var saved = repository.save(entity);
        if (entity.getAttachments() != null && !entity.getAttachments().isEmpty()) {
            List<FileEntity> savedAttachments = new ArrayList<>();

            for (FileEntity file : entity.getAttachments()) {
                file.setMessage(saved); // Устанавливаем связь с сохраненным сообщением
                savedAttachments.add(fileRepository.save(file)); // Сохраняем каждый файл
            }

            saved.setAttachments(savedAttachments);
        }
        return saved;
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
