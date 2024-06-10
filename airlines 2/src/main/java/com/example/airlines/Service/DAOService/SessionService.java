package com.example.airlines.Service.DAOService;

import com.example.airlines.Entity.Session;
import com.example.airlines.Entity.User;
import com.sun.istack.NotNull;

import java.util.Optional;

public interface SessionService {

    @NotNull
    Session createSession(
        @NotNull User user,
        @NotNull String token
    );

    Optional<Session> findByToken(
        @NotNull String token
    );

}
