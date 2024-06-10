package com.example.airlines.Controllers;

import com.example.airlines.Entity.User;
import com.example.airlines.Exception.UserNotFoundException;
import com.example.airlines.Forms.UserLoginForm;
import com.example.airlines.Forms.UserRegisterForm;
import com.example.airlines.Security.JwtTokenProvider;
import com.example.airlines.Service.DAOService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    private final JwtTokenProvider jwtTokenProvider;


    @Autowired
    public AuthController(
        UserService userService,
        JwtTokenProvider jwtTokenProvider
    ) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
        @RequestBody UserRegisterForm user
    ) {
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email is already in use");
        }
        if (!isValidEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Invalid email address");
        }
        if (!isValidPassword(user.getPassword())) {
            return ResponseEntity.badRequest().body("Password must be more than 8 characters, contain letters, numbers, and special characters");
        }

        userService.register(user);
        return ResponseEntity.ok("User successfully registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
        @RequestBody UserLoginForm userLoginForm
    ) {
        String accessToken = userService.login(userLoginForm);

        return ResponseEntity.ok("Login successful. \n Jwt token: " + accessToken);
    }


    @GetMapping("/user")
    public ResponseEntity<?> getUserInfo(
        HttpServletRequest request
    ) {
        String token = jwtTokenProvider.resolveAccessToken(request);
        try {
            String email = jwtTokenProvider.getUsername(token);

            User user = userService.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

            return ResponseEntity.ok(user);
        } catch (UserNotFoundException exc) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }

    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8 && password.matches(".*[A-Za-z].*") && password.matches(".*\\d.*") && password.matches(".*[@#$%^&+=].*");
    }
}
