package com.eduardo.airline.sitting.orchestator;

import com.eduardo.airline.sitting.model.airplane.Airplane;
import com.eduardo.airline.sitting.model.airplane.Row;
import com.eduardo.airline.sitting.model.airplane.Seat;
import com.eduardo.airline.sitting.model.passenger.Group;
import com.eduardo.airline.sitting.model.passenger.Passenger;
import com.eduardo.airline.sitting.model.passenger.WaitingList;
import com.eduardo.airline.sitting.utils.GroupFactory;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SeatOrchestator {

    public static int run(Airplane airplane, WaitingList waitingList) {

        int totalPassengers = getTotalPassengers(waitingList);
        int unsatisfiedPassengers = 0;
        int maxSize = airplane.getRowMaxSize();

        //Assign seats together
        for (int i = maxSize; i > 0; --i) {
            List<Group> groups = waitingList.getGroups(i);
            List<Row> rows = airplane.getRowsWithTogetherSeats(i);
            for (Group group: groups) {
                SeatsMatch bestMatch = null;
                for (Row row: rows) {
                    List<List<Seat>> togetherSeats = row.getTogetherSeats(i);
                    for (List<Seat> seats: togetherSeats) {
                        SeatsMatch currentMatch = getMatch(group, seats);
                        bestMatch = getBestMatch(bestMatch, currentMatch);
                    }
                }
                unsatisfiedPassengers += assign(group, bestMatch.getSeats());
                waitingList.removeGroup(group);
            }
        }

        //Assign any seat
        for (Group group: waitingList.getGroups()) {
            for (Passenger passenger: group.getAllPassengers()){
                Row oneSeatRow = airplane.getRowsWithTogetherSeats(1).get(0);
                if (oneSeatRow != null) {
                    Group onePassengerGroup = GroupFactory.createGroup();
                    onePassengerGroup.addPassenger(passenger);
                    assign(onePassengerGroup, oneSeatRow.getTogetherSeats(1).get(0));
                    ++unsatisfiedPassengers;
                }
            }
        }

        int satisfactionIndex = (int)(((float)(totalPassengers - unsatisfiedPassengers)/totalPassengers) * 100);

        return satisfactionIndex;
    }



    private static int getTotalPassengers(WaitingList waitingList) {
        return (int)waitingList.getGroups().stream().flatMap(
                group ->
                    group.getAllPassengers().stream()
        ).count();
    }

    private static int assign(Group group, List<Seat> seats) {
        int unsatisfiedPassengers = 0;
        boolean ascOrder = true;
        Seat firstSeat = seats.get(0);
        List<Passenger> windowPassengers = group.getWindowPassengers();
        int windowPassengersCount = windowPassengers.size();
        if ((!firstSeat.isWindow() && (windowPassengersCount > 0)) ||
                (firstSeat.isWindow() && (windowPassengersCount == 0))) {

            ascOrder = false;
        }

        if (!ascOrder) {
            Collections.reverse(seats);
        }

        int seatIndex = 0;
        if (windowPassengersCount > 0) {
            Passenger firstWindowPassenger = windowPassengers.get(0);
            Seat seat = seats.get(seatIndex++);
            seat.setPassenger(firstWindowPassenger);
            windowPassengers.remove(firstWindowPassenger);
            if (!seat.isWindow()) {
                ++unsatisfiedPassengers;
            }
        }

        List<Passenger> nonWindowPassengers = group.getNonWindowPassengers();
        for (Passenger passenger: nonWindowPassengers) {
            seats.get(seatIndex++).setPassenger(passenger);
        }

        for (Passenger passenger: windowPassengers) {
            Seat seat = seats.get(seatIndex++);
            seat.setPassenger(passenger);
            if (!seat.isWindow()) {
                unsatisfiedPassengers++;
            }
        }

        return unsatisfiedPassengers;
    }


    private static SeatsMatch getBestMatch(SeatsMatch bestMatch, SeatsMatch currentMatch) {
        SeatsMatch result = bestMatch;
        if (bestMatch == null) {
            result = currentMatch;
        }
        else {
            result = currentMatch.compareTo(bestMatch) < 0?currentMatch:bestMatch;
        }

        return result;
    }

    private static SeatsMatch getMatch(Group group, List<Seat> seats) {
        int extraWindows = - group.getWindowPassengers().size();
        for (Seat seat: seats) {
            if (seat.isWindow()) {
                ++extraWindows;
            }
        }

        int extraSeats = seats.size()-group.size();
        return new SeatsMatch(seats, extraSeats, extraWindows);
    }

    /**
     * Offset:
     *  0 if the number we need is the same we have.
     *  n > 0 for any extra resource we have and don't need.
     *  n < 0 for any resource we need but we don't have.
     */
    private static class SeatsMatch implements Comparable<SeatsMatch> {
        private int seatNumberOffset;
        private int windowNumberOffset;
        private List<Seat> seats;

        public List<Seat> getSeats() {
            return seats;
        }

        public int getSeatNumberOffset() {
            return  seatNumberOffset;
        }

        public int getWindowNumberOffset() {
            return windowNumberOffset;
        }

        SeatsMatch(List<Seat> seats, int seatNumberOffset, int windowNumberOffset) {
            this.seats = seats;
            this.seatNumberOffset = seatNumberOffset;
            this.windowNumberOffset = windowNumberOffset;
        }

        @Override
        public int compareTo(SeatsMatch o) {

            int result = 1;
            int otherSeatNumberOffset = o.getSeatNumberOffset();

            if ((otherSeatNumberOffset >= 0 && seatNumberOffset >= 0) ||
                    (otherSeatNumberOffset < 0 && seatNumberOffset < 0)) {

                int otherWindowNumberOffset = o.getWindowNumberOffset();


                if ((otherWindowNumberOffset >= 0 && windowNumberOffset >= 0) ||
                        (otherWindowNumberOffset < 0 && windowNumberOffset < 0)) {

                    if (otherSeatNumberOffset != seatNumberOffset) {
                        if (Math.abs(seatNumberOffset) == Math.min(
                                Math.abs(seatNumberOffset), Math.abs(otherSeatNumberOffset))) {

                            result = -1;
                        }
                    } else {

                        if (otherWindowNumberOffset != windowNumberOffset) {
                            if (Math.abs(windowNumberOffset) == Math.min(
                                    Math.abs(windowNumberOffset), Math.abs(otherWindowNumberOffset))) {

                                result = -1;
                            }
                        }
                    }
                } else if (windowNumberOffset >= 0) {
                    result = -1;
                }

            } else if (seatNumberOffset >= 0) {
                result = -1;
            }
            return result;
        }
    }
}
