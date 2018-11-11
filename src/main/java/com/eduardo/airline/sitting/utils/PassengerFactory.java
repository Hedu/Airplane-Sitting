package com.eduardo.airline.sitting.utils;

import com.eduardo.airline.sitting.model.passenger.Passenger;
import com.eduardo.airline.sitting.model.passenger.impl.PassengerImpl;

public class PassengerFactory {

    public static Passenger createPassenger(String name, boolean wantsWindow) {
        return new PassengerImpl(name, wantsWindow);
    }
}
