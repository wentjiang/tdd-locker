package com.wentjiang.locker;

import com.wentjiang.locker.exception.BadTicketException;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public abstract class LockerRobotBase implements BagOperate {

    protected final List<Locker> lockers;

    public LockerRobotBase(List<Locker> lockers) {
        this.lockers = lockers;
    }

    @Override
    public abstract Ticket storeBag(Bag bag);

    @Override
    public Bag takeOutBag(Ticket ticket) {
        for (Locker locker : lockers) {
            try {
                return locker.takeOutBag(ticket);
            } catch (BadTicketException ignored) {
            }
        }
        throw new BadTicketException();
    }

    public boolean isNotFull() {
        return lockers.stream().anyMatch(locker -> locker.getFreeCapacity() > 0);
    }
}
