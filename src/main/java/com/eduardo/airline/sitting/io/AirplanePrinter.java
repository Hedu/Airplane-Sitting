package com.eduardo.airline.sitting.io;

import com.eduardo.airline.sitting.model.airplane.Airplane;
import com.eduardo.airline.sitting.model.airplane.Row;
import com.eduardo.airline.sitting.model.airplane.Seat;
import com.eduardo.airline.sitting.model.passenger.Passenger;

import java.util.List;
import java.util.stream.Collectors;

public class AirplanePrinter {

    public static void printAirplane(Airplane airplane) {
        List<Row> rows = airplane.getRows();

        for (Row row: rows) {
            List<String> names = row.getSeats().stream().map(
                    seat -> {
                        if (!seat.isEmpty()) {
                            Passenger passenger = seat.getPassenger();
                            return passenger.getName();
                        } else {
                            return "X";
                        }
                    }
            ).collect(Collectors.toList());

            String output = String.join(" ", names);

            System.out.println(output);
        }
    }
}
