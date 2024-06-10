package com.example.airlines.Service.DAOService;

import com.example.airlines.Entity.User;
import com.example.airlines.Forms.UserLoginForm;
import com.example.airlines.Forms.UserRegisterForm;
import com.sun.istack.NotNull;

import java.util.Optional;

public interface UserService {

    @NotNull
    User register(
        @NotNull UserRegisterForm userRegisterForm
    );

    @NotNull
    String login(
        @NotNull UserLoginForm userLoginForm
    );

    Optional<User> findByEmail(
        @NotNull String email
    );
}
