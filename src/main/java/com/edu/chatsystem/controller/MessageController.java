package com.edu.chatsystem.controller;

import com.edu.chatsystem.configuration.Constants;
import com.edu.chatsystem.dto.FileDto;
import com.edu.chatsystem.dto.MessageDto;
import com.edu.chatsystem.model.FileEntity;
import com.edu.chatsystem.model.MessageEntity;
import com.edu.chatsystem.service.ChatService;
import com.edu.chatsystem.service.FileService;
import com.edu.chatsystem.service.MessageService;
import com.edu.chatsystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(Constants.API_URL + "/message")
public class MessageController {
    private final MessageService messageService;
    private final UserService userService;
    private final ChatService chatService;
    private final ModelMapper modelMapper;
    private FileService fileService;

    public MessageController(MessageService messageService, UserService userService, ChatService chatService, ModelMapper modelMapper, FileService fileService) {
        this.messageService = messageService;
        this.userService = userService;
        this.chatService = chatService;
        this.modelMapper = modelMapper;
        this.fileService = fileService;
    }

    private MessageDto toDto(MessageEntity entity) {
        var msg = new MessageDto(
                entity.getId(),
                entity.getChat().getId(),
                entity.getUser().getId(),
                entity.getText(),
                entity.getIsFavorite(),
                entity.getCreatedAt(),
                new ArrayList<>());


        var files = entity.getAttachments();
        for (var file : files) {
            var f = new FileDto(
                    file.getId(),
                    file.getName(),
                    file.getType(),
                    file.getData(),
                    entity.getId()
            );
            msg.addAttachment(f);
        }
        return msg;
    }

    private MessageEntity toEntity(MessageDto dto) {
        if (dto.getUserId() == null)
        {
            return new MessageEntity(
                    dto.getText()
            );
        }

        return new MessageEntity(
                chatService.get(dto.getChatId()),
                userService.get(dto.getUserId()),
                dto.getText(),
                dto.isFavorite(),
                dto.getCreatedAt()
        );
    }

    private MessageEntity toEntity(MessageDto dto, MultipartFile file) {
        MessageEntity ent = toEntity(dto); // Используем существующий метод

        if (ent.getAttachments() == null) {
            ent.setAttachments(new ArrayList<>());
        }

        if (file != null && !file.isEmpty()) {
            FileEntity fileEntity = new FileEntity(); // Создаем новый объект
            fileEntity.setName(file.getOriginalFilename());
            fileEntity.setType(file.getContentType());
            try {
                fileEntity.setData(file.getBytes());
                ent.getAttachments().add(fileEntity); // Добавляем новый файл
            } catch (IOException e) {
                throw new RuntimeException("Failed to process file", e);
            }
        }
        return ent;
    }

    @GetMapping
    public List<MessageDto> getAll() {
        return messageService.getAll().stream().map(this::toDto).toList();
    }

    @GetMapping("/chat/{id}")
    public List<MessageDto> getAllByChat(@PathVariable(name = "id") Long chat_id) {
        return messageService.getAllbyChat(chat_id).stream().map(this::toDto).toList();
    }

    @GetMapping("/{id}")
    public MessageDto get(@PathVariable(name = "id") Long id) {
        return toDto(messageService.get(id));
    }

    @PostMapping
    public MessageDto create(@RequestBody @Valid MessageDto dto) {
        return toDto(messageService.create(toEntity(dto)));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MessageDto> create(
            @RequestPart MessageDto dto,
            @RequestPart(required = false) MultipartFile file) {

        MessageDto createdMessage = toDto(messageService.create(toEntity(dto, file)));
        return ResponseEntity.ok(createdMessage);
    }

    @PutMapping("/{id}")
    public MessageDto update(
            @PathVariable(name = "id") Long id,
            @RequestBody @Valid MessageDto dto) {
        return toDto(messageService.update(id, toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public MessageDto delete(@PathVariable(name = "id") Long id) {
        return toDto(messageService.delete(id));
    }
}