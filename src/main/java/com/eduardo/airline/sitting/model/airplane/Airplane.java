package com.eduardo.airline.sitting.model.airplane;

import java.util.List;

public interface Airplane {

    List<Row> getRows();

    List<Row> getRowsWithTogetherSeats(int seats);

    int getRowsCount();

    int getRowMaxSize();

}
