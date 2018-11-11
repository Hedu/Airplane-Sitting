package com.eduardo.airline.sitting.model.airplane.impl;

import com.eduardo.airline.sitting.model.airplane.Airplane;
import com.eduardo.airline.sitting.model.airplane.Row;
import com.eduardo.airline.sitting.utils.AirplaneBuilder;
import com.eduardo.airline.sitting.utils.RowFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AirplaneImpl implements Airplane {
    private List<Row> rows;
    private int rowMaxSize;

    private AirplaneImpl(List<Row> rows, int rowMaxSize) {
        this.rows = rows;
        this.rowMaxSize = rowMaxSize;
    }

    @Override
    public List<Row> getRows() {
        return rows;
    }

    @Override
    public List<Row> getRowsWithTogetherSeats(int seats) {
        return rows.stream().filter(
                row -> row.getMaxEmptyTogetherSeatsCount() >= seats
            ).collect(Collectors.toList());
    }

    @Override
    public int getRowMaxSize() {
        return rowMaxSize;
    }

    @Override
    public int getRowsCount() {
        return rows.size();
    }

    public static class AirplaneBuilderImpl implements AirplaneBuilder {
        private List<Row> rows = new ArrayList<>();
        private int rowMaxSize = 0;

        @Override
        public Airplane build() {
            return new AirplaneImpl(rows, rowMaxSize);
        }

        @Override
        public void addRows(int count, int rowSize) {
            for (int i = 0; i < count; ++i) {
                rows.add(RowFactory.createRow(rowSize));
            }
            rowMaxSize = Math.max(rowMaxSize, rowSize);
        }
    }
}
