package com.edu.chatsystem.controller;

import com.edu.chatsystem.configuration.Constants;
import com.edu.chatsystem.dto.MessageDto;
import com.edu.chatsystem.model.MessageEntity;
import com.edu.chatsystem.service.ChatService;
import com.edu.chatsystem.service.MessageService;
import com.edu.chatsystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Constants.API_URL + "/message")
public class MessageController {
    private final MessageService messageService;
    private final UserService userService;
    private final ChatService chatService;
    private final ModelMapper modelMapper;

    public MessageController(MessageService messageService, UserService userService, ChatService chatService, ModelMapper modelMapper) {
        this.messageService = messageService;
        this.userService = userService;
        this.chatService = chatService;
        this.modelMapper = modelMapper;
    }

    private MessageDto toDto(MessageEntity entity) {
        return new MessageDto(
                entity.getId(),
                entity.getChat().getId(),
                entity.getUser().getId(),
                entity.getText(),
                entity.getIsFavorite(),
                entity.getCreatedAt()
        );
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