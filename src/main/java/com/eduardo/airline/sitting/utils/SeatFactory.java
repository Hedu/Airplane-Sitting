package com.eduardo.airline.sitting.utils;

import com.eduardo.airline.sitting.model.airplane.Seat;
import com.eduardo.airline.sitting.model.airplane.impl.SeatImpl;

public class SeatFactory {

    public static Seat createSeat() {
        return createSeat(false);
    }

    public static Seat createSeat(boolean isWindow) {
        return new SeatImpl(isWindow);
    }
}
