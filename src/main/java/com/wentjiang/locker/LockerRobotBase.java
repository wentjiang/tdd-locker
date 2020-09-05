package com.wentjiang.locker;

import com.wentjiang.locker.exception.BadTicketException;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public abstract class LockerRobotBase {

    protected final List<Locker> lockers;

    public LockerRobotBase(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public abstract Ticket storeBag(Bag bag);

    public Bag takeOutBag(Ticket ticket) {
        AtomicReference<Bag> takeOutBag = new AtomicReference<>();
        lockers.forEach(locker -> {
            Optional<Bag> bagOptional = locker.takeOutBag(ticket);
            bagOptional.ifPresent(takeOutBag::set);
        });
        Bag bag = takeOutBag.get();
        if (bag == null){
            throw new BadTicketException();
        }else {
            return bag;
        }
    }
}
