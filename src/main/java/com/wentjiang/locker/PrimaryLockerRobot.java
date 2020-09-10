package com.wentjiang.locker;

import com.wentjiang.locker.exception.CapacityFullException;

import java.util.List;
import java.util.Optional;

public class PrimaryLockerRobot extends LockerRobotBase {

    public PrimaryLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    @Override
    public Ticket storeBag(Bag bag) {
        Optional<BagOperate> lockerOptional = bagOperates.stream().filter(BagOperate::isNotFull).findFirst();
        return lockerOptional.orElseThrow(CapacityFullException::new).storeBag(bag);
    }
}
