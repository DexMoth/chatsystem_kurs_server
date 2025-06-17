package com.edu.chatsystem.controller;

import com.edu.chatsystem.configuration.Constants;
import com.edu.chatsystem.dto.FileDto;
import com.edu.chatsystem.model.FileEntity;
import com.edu.chatsystem.service.ChatService;
import com.edu.chatsystem.service.FileService;
import com.edu.chatsystem.service.MessageService;
import com.edu.chatsystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Constants.API_URL + "/file")
public class FileController {
    private final FileService fileService;
    private final MessageService messageService;
    private final ModelMapper modelMapper;

    public FileController(FileService FileService, MessageService messageService, ModelMapper modelMapper) {
        this.fileService = FileService;
        this.messageService = messageService;
        this.modelMapper = modelMapper;
    }

    private FileDto toDto(FileEntity entity) {
        FileDto dto = new FileDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setType(entity.getType());
        dto.setData(entity.getData());
        dto.setMessageId(entity.getMessage() != null ? entity.getMessage().getId() : null);
        return dto;
    }

    private FileEntity toEntity(FileDto dto) {
        FileEntity entity = new FileEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setType(dto.getType());
        entity.setData(dto.getData());
        entity.setMessage(messageService.get(dto.getMessageId()));
        return entity;
    }

    @GetMapping
    public List<FileDto> getAll() {
        return fileService.getAll().stream().map(this::toDto).toList();
    }

    @GetMapping("/{id}")
    public FileDto get(@PathVariable(name = "id") Long id) {
        return toDto(fileService.get(id));
    }

    @PostMapping
    public FileDto create(@RequestBody @Valid FileDto dto) {
        return toDto(fileService.create(toEntity(dto)));
    }

    @PutMapping("/{id}")
    public FileDto update(
            @PathVariable(name = "id") Long id,
            @RequestBody @Valid FileDto dto) {
        return toDto(fileService.update(id, toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public FileDto delete(@PathVariable(name = "id") Long id) {
        return toDto(fileService.delete(id));
    }

    @PostMapping("/upload")
    public ResponseEntity<FileDto> uploadFile(@RequestParam("file") MultipartFile file) {
        FileEntity savedFile = fileService.saveFile(file);
        return ResponseEntity.ok(toDto(savedFile));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        FileEntity file = fileService.get(id);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"")
                .body(file.getData());
    }
}