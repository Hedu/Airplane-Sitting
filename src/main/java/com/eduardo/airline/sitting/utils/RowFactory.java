package com.eduardo.airline.sitting.utils;

import com.eduardo.airline.sitting.model.airplane.Row;
import com.eduardo.airline.sitting.model.airplane.impl.RowImpl;

public class RowFactory {

    public static Row createRow(int rowLength) {
        return new RowImpl(rowLength);
    }
}
