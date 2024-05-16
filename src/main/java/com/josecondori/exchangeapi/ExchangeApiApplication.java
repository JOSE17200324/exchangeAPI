package com.josecondori.exchangeapi;

import com.josecondori.exchangeapi.infrastructure.db.springdata.dbo.entities.Role;
import com.josecondori.exchangeapi.infrastructure.db.springdata.dbo.entities.UserEntity;
import com.josecondori.exchangeapi.domain.models.enums.RoleEnum;
import com.josecondori.exchangeapi.application.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
@EnableFeignClients
@RequiredArgsConstructor
public class ExchangeApiApplication {

	private final PasswordEncoder passwordEncoder;

	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(ExchangeApiApplication.class, args);
	}

	@Bean
	CommandLineRunner init() {
		return args -> {
			UserEntity admin = UserEntity.builder()
					.email("jose@gmail.com")
					.username("jose")
					.password(passwordEncoder.encode("123"))
					.roles(Set.of(Role.builder()
							.name(RoleEnum.valueOf(RoleEnum.ADMIN.name()))
							.build()))
					.build();
			userRepository.save(admin);
		};

	}

}
