package com.edu.chatsystem.service;

import com.edu.chatsystem.error.NotFoundException;
import com.edu.chatsystem.model.FileEntity;
import com.edu.chatsystem.model.MessageEntity;
import com.edu.chatsystem.repository.FileRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class FileService {
    private final FileRepository repository;
    public FileService(FileRepository repository) {
        this.repository = repository;
    }
    @Transactional
    public List<FileEntity> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).toList();
    }

    @Transactional
    public FileEntity get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(FileEntity.class, id));
    }
    @Transactional
    public FileEntity create(FileEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity is null");
        }
        return repository.save(entity);
    }

    @Transactional
    public FileEntity update(Long id, FileEntity ent) {
        final FileEntity el = get(id);
        return repository.save(el);
    }

    @Transactional
    public FileEntity delete(Long id) {
        final FileEntity el = get(id);
        repository.delete(el);
        return el;
    }

    //для сохранения из MultipartFile
    @Transactional
    public FileEntity saveFile(MultipartFile file) {
        try {
            FileEntity fileEntity = new FileEntity();
            fileEntity.setName(file.getOriginalFilename());
            fileEntity.setType(file.getContentType());
            fileEntity.setData(file.getBytes());
            return repository.save(fileEntity);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    //для получения файлов сообщения
    @Transactional
    public List<FileEntity> getFilesByMessage(MessageEntity message) {
        return repository.findByMessageId(message.getId());
    }
}
