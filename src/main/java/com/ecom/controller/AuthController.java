package com.ecom.controller;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.RegistationDTO;
import com.ecom.entity.Users;
import com.ecom.repository.UsersRepository;
import com.ecom.security.JwtService;
import com.ecom.utility.ApiResponse;

@RestController
@RequestMapping("/api/auth")
//@RequiredArgsConstructor
public class AuthController {

	private final UsersRepository usersRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	public AuthController(UsersRepository usersRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
		this.usersRepository = usersRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}

	@PostMapping("/register")
	public ResponseEntity<ApiResponse<?>> registerUser(@RequestBody RegistationDTO request) throws Exception {
		if (usersRepository.findByEmail(request.getEmail()).isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(ApiResponse.of("email already register!", HttpStatus.BAD_REQUEST.value(), null));
		}
		Long id = usersRepository.findMaxId();
		id = (id == 0) ? 1001L : id + 1;
		Users user = new Users();

		user.setUserId(id);
		user.setEmail(request.getEmail());
		user.setUsername(request.getUsername());
		user.setEntryDate(new Timestamp(System.currentTimeMillis()));
		user.setPhone(request.getPhone());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setIsvalid(true);

		usersRepository.save(user);

		String token = jwtService.generateTokrn(request.getEmail());

		return ResponseEntity.ok(ApiResponse.of("Register sucesfully !", HttpStatus.OK.value(), token));

	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<?>> login(@RequestBody RegistationDTO request) throws Exception {
		Optional<Users> user = usersRepository.findByEmail(request.getEmail());
		if (user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(ApiResponse.of("Invalid username or password!", HttpStatus.BAD_REQUEST.value(), null));
		}
		if (!passwordEncoder.matches( request.getPassword(),user.get().getPassword())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(ApiResponse.of("Invalid username or password!", HttpStatus.BAD_REQUEST.value(), null));
		}
		String token = jwtService.generateTokrn(request.getEmail());

		return ResponseEntity.ok(ApiResponse.of("Login sucesfully !", HttpStatus.OK.value(), token));
	}

}
