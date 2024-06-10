package com.example.airlinesBuy.Service;

import com.example.airlinesBuy.Entity.Station;
import com.sun.istack.NotNull;

public interface StationService {

    @NotNull Station getStationByName(@NotNull String name);
}
