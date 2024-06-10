package com.example.airlines.Repository;

import com.example.airlines.Entity.Session;
import com.sun.istack.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByToken(@Param(value = "token") @NotNull String token);

    Optional<Session> findFirstByUserId(@Param(value = "token") @NotNull Long userId);
}
