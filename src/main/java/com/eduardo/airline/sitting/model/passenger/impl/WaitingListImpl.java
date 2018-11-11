package com.eduardo.airline.sitting.model.passenger.impl;

import com.eduardo.airline.sitting.model.passenger.Group;
import com.eduardo.airline.sitting.model.passenger.WaitingList;
import com.eduardo.airline.sitting.utils.WaitingListBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WaitingListImpl implements WaitingList {
    private List<Group> groups;

    private WaitingListImpl(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public void removeGroup(Group group) {
        groups.remove(group);
    }

    @Override
    public List<Group> getGroups() {
        return groups;
    }

    @Override
    public List<Group> getGroups(int groupSize) {
        return groups.stream().filter(group -> group.size() == groupSize).collect(Collectors.toList());
    }

    public static class WaitingListBuilderImpl implements WaitingListBuilder {
        private List<Group> groups = new ArrayList<>();

        @Override
        public void addGroup(Group group) {
            groups.add(group);
        }

        @Override
        public WaitingListImpl build() {
            return new WaitingListImpl(groups);
        }
    }
}
