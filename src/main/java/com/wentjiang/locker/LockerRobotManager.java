package com.wentjiang.locker;

import com.wentjiang.locker.exception.BadTicketException;
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
        Optional<LockerRobotBase> lockerRobotBaseOptional = robots.stream().filter(LockerRobotBase::isNotFull).findFirst();
        if (lockerRobotBaseOptional.isPresent()) {
            return lockerRobotBaseOptional.get().storeBag(bag);
        }
        return lockers.stream().filter(locker -> locker.getFreeCapacity() > 0).findFirst().orElseThrow(CapacityFullException::new).storeBag(bag);
    }

    public Bag takeOutBag(Ticket ticket) {
        for (LockerRobotBase robot : robots) {
            try {
                return robot.takeOutBag(ticket);
            } catch (BadTicketException ignored) {
            }
        }
        for (Locker locker : lockers) {
            Optional<Bag> bagOptional = locker.takeOutBag(ticket);
            if (bagOptional.isPresent()) {
                return bagOptional.get();
            }
        }
        throw new BadTicketException();
    }
}
