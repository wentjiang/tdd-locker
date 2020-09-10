package com.wentjiang.locker;

import com.wentjiang.locker.exception.CapacityFullException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SmartLockerRobot extends LockerRobotBase {
    public SmartLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    @Override
    public Ticket storeBag(Bag bag) {
        Optional<BagOperate> lockerOptional = bagOperates.stream().max(Comparator.comparingInt(BagOperate::getFreeCapacity));
        return lockerOptional.orElseThrow(CapacityFullException::new).storeBag(bag);
    }
}
