package com.edu.chatsystem.controller;

import com.edu.chatsystem.configuration.Constants;
import com.edu.chatsystem.dto.UserDto;
import com.edu.chatsystem.model.UserEntity;
import com.edu.chatsystem.repository.UserRepository;
import com.edu.chatsystem.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

@RestController
@RequestMapping(Constants.API_URL + "/user")
public class UserController {
    private  final UserRepository userRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserRepository userRepository, UserService userService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    private UserDto toDto(UserEntity entity) {
        return new UserDto(
                entity.getId(),
                entity.getLogin(),
                entity.getName(),
                entity.getPassword(),
                entity.getPhone(),
                entity.getReportCardNumber()
        );
    }

    private UserEntity toEntity(UserDto dto) {
        var ent = modelMapper.map(dto, UserEntity.class);
        return ent;
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser(HttpSession session) {
        UserEntity sessionUser = (UserEntity) session.getAttribute("user");
        if (sessionUser == null) {
            return ResponseEntity.status(401).build();
        }

        UserEntity user = userService.get(sessionUser.getId());

        return ResponseEntity.ok(new UserDto(
                user.getId(),
                user.getLogin(),
                user.getName(),
                user.getPassword(),
                user.getPhone(),
                user.getReportCardNumber()
        ));
    }

    @PostMapping
    public ResponseEntity<?> create (@Valid @RequestBody UserEntity request,
                                          BindingResult bindingResult) {

        // Валидация входных данных
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            // Создание пользователя
            UserEntity user = new UserEntity();
            user.setLogin(request.getLogin());
            user.setName(request.getName());
            user.setPassword(request.getPassword());
            user.setPhone(request.getPhone());
            user.setReportCardNumber(request.getReportCardNumber());
            System.out.println(user);

            // Сохранение пользователя
            UserEntity createdUser = userService.create(user);

            return ResponseEntity.ok(Collections.singletonMap("message", "User registered successfully"));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Registration failed: " + e.getMessage()));
        }
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll().stream().map(this::toDto).toList();
    }

    @GetMapping("/{id}")
    public UserDto get(@PathVariable(name = "id") Long id) {
        return toDto(userService.get(id));
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable(name = "id") Long id, @RequestBody UserDto dto) {
        return toDto(userService.update(id, toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public UserDto delete(@PathVariable(name = "id") Long id) {
        return toDto(userService.delete(id));
    }
}
