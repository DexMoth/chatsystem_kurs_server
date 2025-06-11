package com.edu.chatsystem;
import com.edu.chatsystem.model.UserEntity;
import com.edu.chatsystem.repository.UserRepository;
import com.edu.chatsystem.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatsystemApplication implements CommandLineRunner {

	public ChatsystemApplication() {
	}

	public static void main(String[] args) {
		SpringApplication.run(ChatsystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
