package com.eduardo.airline.sitting.model.airplane;

import java.util.List;

public interface Row {
    int size();

    int getMaxEmptyTogetherSeatsCount();

    List<Seat> getSeats();

    List<List<Seat>> getTogetherSeats(int count);
}
