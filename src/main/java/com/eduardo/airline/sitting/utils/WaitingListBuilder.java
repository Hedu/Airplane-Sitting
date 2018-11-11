package com.eduardo.airline.sitting.utils;

import com.eduardo.airline.sitting.model.passenger.Group;
import com.eduardo.airline.sitting.model.passenger.impl.WaitingListImpl;

public interface WaitingListBuilder {
    void addGroup(Group group);

    WaitingListImpl build();
}
