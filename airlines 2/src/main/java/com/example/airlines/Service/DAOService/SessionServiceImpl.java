package com.example.airlines.Service.DAOService;

import com.example.airlines.Entity.Session;
import com.example.airlines.Entity.User;
import com.example.airlines.Repository.SessionRepository;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;
    private final int sessionExpiryHours;

    @Autowired
    public SessionServiceImpl(
        SessionRepository sessionRepository,
        @Value("${session.expiry.hours}") int sessionExpiryHours
    ) {
        this.sessionRepository = sessionRepository;
        this.sessionExpiryHours = sessionExpiryHours;
    }

    @NotNull
    @Override
    public Session createSession(@NotNull User user, @NotNull String token) {
        LocalDateTime expiryTime = LocalDateTime.now().plusHours(sessionExpiryHours);
        Session session = sessionRepository.findFirstByUserId(user.getId())
            .orElseGet(() -> {
                Session newSession = new Session();
                newSession.setUser(user);
                return newSession;
            });

        session.setToken(token);
        session.setExpires(expiryTime);
        return sessionRepository.save(session);
    }

    @Override
    public Optional<Session> findByToken(
        @NotNull String token
    ) {
        return sessionRepository.findByToken(token);
    }
}
