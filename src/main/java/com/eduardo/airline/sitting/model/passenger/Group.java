package com.eduardo.airline.sitting.model.passenger;

import java.util.List;

public interface Group {
    void addPassenger(Passenger passenger);

    int size();

    boolean hasWindowPassengers();

    List<Passenger> getAllPassengers();

    List<Passenger> getWindowPassengers();

    List<Passenger> getNonWindowPassengers();
}
