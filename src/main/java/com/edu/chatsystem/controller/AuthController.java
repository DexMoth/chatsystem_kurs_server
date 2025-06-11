package com.edu.chatsystem.controller;

import com.edu.chatsystem.configuration.Constants;
import com.edu.chatsystem.dto.AuthDto;
import com.edu.chatsystem.model.UserEntity;
import com.edu.chatsystem.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.API_URL + "/auth")
@SessionAttributes("user")
public class AuthController {
    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Вход
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDto dto, HttpSession session) {
        UserEntity user = userRepository.findByLoginIgnoreCase(dto.getLogin())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        if (!user.getPassword().equals(dto.getPassword())) {
            return ResponseEntity.badRequest().body("Неверный пароль");
        }

        // Сохраняем пользователя в сессии
        session.setAttribute("user", user);
        return ResponseEntity.ok("Вход выполнен");
    }

    // Выход
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Выход выполнен");
    }

    // Проверка авторизации
    @GetMapping("/check")
    public ResponseEntity<?> checkAuth(HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(401).body("Не авторизован");
        }
        return ResponseEntity.ok(user);
    }
}
