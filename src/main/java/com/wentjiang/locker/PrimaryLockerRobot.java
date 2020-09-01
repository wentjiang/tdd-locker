package com.wentjiang.locker;

import java.util.List;
import java.util.Optional;

public class PrimaryLockerRobot {

    private List<Locker> lockers;

    public PrimaryLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket storeBag(Bag bag) {
        Optional<Locker> lockerOptional = lockers.stream().filter(locker -> !locker.isFull()).findFirst();
        return lockerOptional.get().storeBag(bag);
    }
}
