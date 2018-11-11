package com.eduardo.airline.sitting.model.passenger.impl;

import com.eduardo.airline.sitting.model.passenger.Group;
import com.eduardo.airline.sitting.model.passenger.Passenger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GroupImpl implements Group {
    private List<Passenger> passengers = new ArrayList<>();

    @Override
    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    @Override
    public int size() {
        return passengers.size();
    }

    @Override
    public boolean hasWindowPassengers() {
        return getWindowPassengers().size() > 0;
    }

    @Override
    public List<Passenger> getAllPassengers() {
        return passengers;
    }

    @Override
    public List<Passenger> getWindowPassengers() {
        return passengers.stream().filter(Passenger::isWantsWindow).collect(Collectors.toList());
    }

    @Override
    public List<Passenger> getNonWindowPassengers() {
        return passengers.stream().filter(passenger -> !passenger.isWantsWindow()).collect(Collectors.toList());
    }
}
