package com.eduardo.airline.sitting.utils;

import com.eduardo.airline.sitting.model.airplane.Airplane;

public interface AirplaneBuilder {
    Airplane build();

    void addRows(int count, int rowSize);
}
