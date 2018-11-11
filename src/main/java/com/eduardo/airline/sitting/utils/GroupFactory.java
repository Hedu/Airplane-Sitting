package com.eduardo.airline.sitting.utils;

import com.eduardo.airline.sitting.model.passenger.Group;
import com.eduardo.airline.sitting.model.passenger.impl.GroupImpl;

public class GroupFactory {

    public static Group createGroup() {
        return new GroupImpl();
    }
}
