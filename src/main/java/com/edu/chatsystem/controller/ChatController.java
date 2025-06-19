package com.edu.chatsystem.controller;

import com.edu.chatsystem.configuration.Constants;
import com.edu.chatsystem.dto.ChatDto;
import com.edu.chatsystem.dto.MessageDto;
import com.edu.chatsystem.model.ChatEntity;
import com.edu.chatsystem.model.UserEntity;
import com.edu.chatsystem.service.ChatService;
import com.edu.chatsystem.service.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Constants.API_URL + "/chat")
public class ChatController {
    private final ChatService chatService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public ChatController(ChatService chatService, UserService userService, ModelMapper modelMapper) {
        this.chatService = chatService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    private ChatDto toDto(ChatEntity entity) {
        if (!entity.getMembers().isEmpty()) {
            return new ChatDto(
                    entity.getId(),
                    entity.getName(),
                    entity.getMembers().stream()
                            .map(UserEntity::getId)
                            .collect(Collectors.toSet())
            );
        } else {
            return new ChatDto(
                entity.getId(),
                entity.getName()
            );
        }
    }

    private ChatEntity toEntity(ChatDto dto) {

        var ent = modelMapper.map(dto, ChatEntity.class);
        if (dto.getMemberIds() != null)
        {
            var members = dto.getMemberIds();
            if (!members.isEmpty()) {
                for (var member : members) {
                    ent.addMember(userService.get(member));
                }
            }
        }
        return ent;
    }

    @GetMapping("/user/{id}")
    public List<ChatDto> getAllByUser(@PathVariable(name = "id") Long user_id) {
        return chatService.getAllbyUser(user_id).stream().map(this::toDto).toList();
    }

    @GetMapping
    public List<ChatDto> getAll() {
        return chatService.getAll().stream().map(this::toDto).toList();
    }

    @GetMapping("/{id}")
    public ChatDto get(@PathVariable(name = "id") Long id) {
        return toDto(chatService.get(id));
    }

    @PutMapping("/{id}/confirm")
    public ChatDto addUser(
            @PathVariable(name = "id") Long id,
            @RequestBody Long userId) {
        return toDto(chatService.addUser(id, userId));
    }

    @PostMapping
    public ChatDto create(@RequestBody @Valid ChatDto dto) {
        return toDto(chatService.create(toEntity(dto)));
    }

    @PutMapping("/{id}")
    public ChatDto update(
            @PathVariable(name = "id") Long id,
            @RequestBody @Valid ChatDto dto) {
        return toDto(chatService.update(id, toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public ChatDto delete(@PathVariable(name = "id") Long id) {
        return toDto(chatService.delete(id));
    }

    @DeleteMapping("/{chatId}/members/{userId}")
    public ChatDto removeMember(
            @PathVariable Long chatId,
            @PathVariable Long userId) {
        return toDto(chatService.removeMember(chatId, userId));
    }


    // Генерация простой ссылки (без токена)
    @GetMapping("/{chatId}/invite-link")
    public ResponseEntity<String> getInviteLink(@PathVariable Long chatId) {
        String link = "http://localhost:5173/join-chat/" + chatId;
        return ResponseEntity.ok(link);
    }

    // Присоединение по ID чата
    @PostMapping("/{chatId}/join")
    public ResponseEntity<?> joinChat(
            @PathVariable Long chatId,
            @RequestParam Long userId) {

        chatService.addUser(chatId, userId);
        return ResponseEntity.ok().build();
    }
}