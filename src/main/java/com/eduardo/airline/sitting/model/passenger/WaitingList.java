package com.eduardo.airline.sitting.model.passenger;

import java.util.List;

public interface WaitingList {
    void removeGroup(Group group);

    List<Group> getGroups();

    List<Group> getGroups(int groupSize);


}
