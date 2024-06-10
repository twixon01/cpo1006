package com.example.airlinesBuy.Service;

import com.example.airlinesBuy.Entity.Station;
import com.example.airlinesBuy.Exception.StationNotFoundException;
import com.example.airlinesBuy.Repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationServiceImpl implements StationService{

    private final StationRepository stationRepository;

    @Autowired
    public StationServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    public Station getStationByName(String name) {
        return stationRepository.findFirstByStation(name)
            .orElseThrow(StationNotFoundException::new);
    }
}
