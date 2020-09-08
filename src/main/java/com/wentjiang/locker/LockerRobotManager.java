package com.wentjiang.locker;

import com.wentjiang.locker.exception.CapacityFullException;

import java.util.List;
import java.util.Optional;

public class LockerRobotManager {

    private final List<Locker> lockers;
    private final List<LockerRobotBase> robots;

    public LockerRobotManager(List<Locker> lockers, List<LockerRobotBase> robots) {
        this.lockers = lockers;
        this.robots = robots;
    }

    public Ticket storeBag(Bag bag) {
        Optional<Locker> optionalLocker = lockers.stream().filter(locker -> locker.getFreeCapacity() > 0).findFirst();
        if (optionalLocker.isPresent()){
            return optionalLocker.get().storeBag(bag);
        }
        return robots.stream().filter(LockerRobotBase::isNotFull).findFirst().orElseThrow(CapacityFullException::new).storeBag(bag);
    }
}
