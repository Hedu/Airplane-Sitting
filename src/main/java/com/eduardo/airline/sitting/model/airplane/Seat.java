package com.eduardo.airline.sitting.model.airplane;

import com.eduardo.airline.sitting.model.passenger.Passenger;

public interface Seat {
    Passenger getPassenger();

    boolean isWindow();

    boolean isEmpty();

    void setPassenger(Passenger passenger);
}
