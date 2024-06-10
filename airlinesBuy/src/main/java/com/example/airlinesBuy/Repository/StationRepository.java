package com.example.airlinesBuy.Repository;

import com.example.airlinesBuy.Entity.Station;
import com.sun.istack.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StationRepository extends JpaRepository<Station, Long> {

    Optional<Station> findFirstByStation(@Param(value = "station") @NotNull String station);

}
