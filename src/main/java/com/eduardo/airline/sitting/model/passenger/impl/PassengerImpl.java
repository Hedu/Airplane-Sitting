package com.eduardo.airline.sitting.model.passenger.impl;

import com.eduardo.airline.sitting.model.passenger.Passenger;

public class PassengerImpl implements Passenger {
    private String name;
    private boolean wantsWindow;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isWantsWindow(){
        return wantsWindow;
    }

    public PassengerImpl(String name, boolean wantsWindow) {
        this.name = name;
        this.wantsWindow = wantsWindow;
    }
}
