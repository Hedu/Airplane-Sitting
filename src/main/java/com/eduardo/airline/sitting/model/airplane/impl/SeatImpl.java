package com.eduardo.airline.sitting.model.airplane.impl;

import com.eduardo.airline.sitting.model.airplane.Seat;
import com.eduardo.airline.sitting.model.passenger.Passenger;

public class SeatImpl implements Seat {
    private Passenger passenger;
    boolean isWindow;

    public SeatImpl(boolean isWindow) {
        this.isWindow = isWindow;
        passenger = null;
    }

    @Override
    public Passenger getPassenger() {
        return passenger;
    }

    @Override
    public boolean isWindow() {
        return isWindow;
    }

    @Override
    public boolean isEmpty() {
        return passenger == null;
    }

    @Override
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}
