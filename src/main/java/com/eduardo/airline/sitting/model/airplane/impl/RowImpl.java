package com.eduardo.airline.sitting.model.airplane.impl;

import com.eduardo.airline.sitting.model.airplane.Row;
import com.eduardo.airline.sitting.model.airplane.Seat;
import com.eduardo.airline.sitting.utils.SeatFactory;

import java.util.ArrayList;
import java.util.List;

public class RowImpl implements Row {
    private List<Seat> seats = new ArrayList<>();

    public RowImpl(int seatsNumber) {
        for (int i = 0; i < seatsNumber; ++i) {
            Seat seat = null;
            if (i==0 || i == seatsNumber - 1) {
                 seat = SeatFactory.createSeat(true);
            } else {
                seat = SeatFactory.createSeat();
            }
            seats.add(seat);
        }
    }

    @Override
    public int size() {
        return seats.size();
    }

    @Override
    public int getMaxEmptyTogetherSeatsCount() {
        int max = 0;
        int current = 0;
        for (Seat seat: seats) {
            if (seat.isEmpty()) {
                ++current;
            }
            else {
                max = Math.max(max, current);
                current = 0;
            }
        }
        return Math.max(max, current);
    }

    @Override
    public List<Seat> getSeats() {
        return seats;
    }

    @Override
    public List<List<Seat>> getTogetherSeats(int count) {
        List<List<Seat>> togetherSeats = new ArrayList<>();
        List<Seat> currentList = new ArrayList<>();
        for (Seat seat: seats) {
            if (seat.isEmpty()) {
                currentList.add(seat);
            }
            else {
                if (currentList.size() >= count) {
                    togetherSeats.add(currentList);
                }
                currentList = new ArrayList<>();
            }
        }

        if (currentList.size() >= count) {
            togetherSeats.add(currentList);
        }
        return togetherSeats;
    }


}
