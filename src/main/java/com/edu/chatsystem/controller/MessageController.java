package com.edu.chatsystem.controller;

import com.edu.chatsystem.configuration.Constants;
import com.edu.chatsystem.dto.MessageDto;
import com.edu.chatsystem.model.MessageEntity;
import com.edu.chatsystem.service.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Constants.API_URL + "/message")
public class MessageController {
    private final MessageService messageService;
    private final ModelMapper modelMapper;

    public MessageController(MessageService messageService, ModelMapper modelMapper) {
        this.messageService = messageService;
        this.modelMapper = modelMapper;
    }

    private MessageDto toDto(MessageEntity entity) {
        return modelMapper.map(entity, MessageDto.class);
    }

    private MessageEntity toEntity(MessageDto dto) {
        return modelMapper.map(dto, MessageEntity.class);
    }

    @GetMapping
    public List<MessageDto> getAll() {
        return messageService.getAll().stream().map(this::toDto).toList();
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