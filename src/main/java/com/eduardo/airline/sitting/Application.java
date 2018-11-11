package com.eduardo.airline.sitting;

import com.eduardo.airline.sitting.io.AirplanePrinter;
import com.eduardo.airline.sitting.io.FileProcessor;
import com.eduardo.airline.sitting.model.airplane.Airplane;
import com.eduardo.airline.sitting.model.airplane.impl.AirplaneImpl;
import com.eduardo.airline.sitting.model.passenger.WaitingList;
import com.eduardo.airline.sitting.model.passenger.impl.WaitingListImpl;
import com.eduardo.airline.sitting.orchestator.SeatOrchestator;

import java.net.URLDecoder;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a file");
        String filename = scanner.nextLine();

        //String filename = URLDecoder.decode(Application.class.getClassLoader().getResource("InputTest").getFile());

        AirplaneImpl.AirplaneBuilderImpl airplaneBuilder = new AirplaneImpl.AirplaneBuilderImpl();
        WaitingListImpl.WaitingListBuilderImpl waitingListBuilder = new WaitingListImpl.WaitingListBuilderImpl();

        FileProcessor.processFile(filename, airplaneBuilder, waitingListBuilder);

        Airplane airplane = airplaneBuilder.build();
        WaitingList waitingList = waitingListBuilder.build();

        int satisfactionIndex = SeatOrchestator.run(airplane, waitingList);

        AirplanePrinter.printAirplane(airplane);
        System.out.println(satisfactionIndex + "%");
    }
}
