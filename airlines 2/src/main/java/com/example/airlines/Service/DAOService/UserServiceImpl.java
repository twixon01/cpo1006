package com.example.airlines.Service.DAOService;

import com.example.airlines.Entity.User;
import com.example.airlines.Exception.UserNotFoundException;
import com.example.airlines.Forms.UserLoginForm;
import com.example.airlines.Forms.UserRegisterForm;
import com.example.airlines.Repository.UserRepository;
import com.example.airlines.Security.JwtTokenProvider;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SessionService sessionService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserServiceImpl(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        SessionService sessionService,
        AuthenticationManager authenticationManager,
        JwtTokenProvider jwtTokenProvider
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.sessionService = sessionService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @NotNull
    @Override
    public User register(
        @NotNull UserRegisterForm userRegisterForm
    ) {
        User user = new User();
        user.setNickname(userRegisterForm.getNickname());
        user.setEmail(userRegisterForm.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterForm.getPassword()));

        return userRepository.save(user);
    }

    @NotNull
    @Override
    public String login(
        @NotNull UserLoginForm userLoginForm
    ) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userLoginForm.getEmail(), userLoginForm.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtTokenProvider.createAccessToken(userLoginForm.getEmail(), "ROLE_USER");

        User user = findByEmail(userLoginForm.getEmail())
            .orElseThrow(UserNotFoundException::new);

        sessionService.createSession(user, accessToken);

        return accessToken;
    }

    @Override
    public Optional<User> findByEmail(
        @NotNull String email
    ) {
        return userRepository.findByEmail(email);
    }

}
