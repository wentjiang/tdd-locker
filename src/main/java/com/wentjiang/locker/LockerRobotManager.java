package com.wentjiang.locker;

import java.util.List;

public class LockerRobotManager {

    private List<Locker> lockers;
    private List<LockerRobotBase> robots;

    public LockerRobotManager(List<Locker> lockers, List<LockerRobotBase> robots) {
        this.lockers = lockers;
        this.robots = robots;
    }

    public Ticket storeBag(Bag bag) {
        return lockers.stream().filter(locker -> locker.getFreeCapacity() > 0).findFirst().get().storeBag(bag);
    }
}