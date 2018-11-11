package com.eduardo.airline.sitting.io;

import com.eduardo.airline.sitting.utils.AirplaneBuilder;
import com.eduardo.airline.sitting.model.passenger.*;
import com.eduardo.airline.sitting.utils.GroupFactory;
import com.eduardo.airline.sitting.utils.PassengerFactory;
import com.eduardo.airline.sitting.utils.WaitingListBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class FileProcessor {

    public static void processFile(
        String fileName, AirplaneBuilder airplaneBuilder, WaitingListBuilder waitingListBuilder) {

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            AtomicInteger linesProcessed = new AtomicInteger(0);
            stream.forEach(
                line -> {
                    if (linesProcessed.get() == 0) {
                        String[] data = line.split(" ");
                        int rows = Integer.parseInt(data[0]);
                        int rowSize = Integer.parseInt(data[1]);
                        airplaneBuilder.addRows(rows, rowSize);
                    } else {
                        processLine(line, waitingListBuilder);
                    }
                    linesProcessed.getAndIncrement();
                }
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processLine(
        String line, WaitingListBuilder waitingListBuilder) {

        String data[] = line.split(" ");
        Group group = GroupFactory.createGroup();

        for (String passengerData: data) {
            boolean wantsWindow = passengerData.endsWith("W");
            String name = wantsWindow?passengerData.substring(0, passengerData.indexOf("W")):passengerData;
            group.addPassenger(PassengerFactory.createPassenger(name, wantsWindow));
        }

        waitingListBuilder.addGroup(group);

    }
}
