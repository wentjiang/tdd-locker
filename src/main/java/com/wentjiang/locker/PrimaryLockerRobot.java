package com.wentjiang.locker;

import com.wentjiang.locker.exception.BadTicketException;
import com.wentjiang.locker.exception.CapacityFullException;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class PrimaryLockerRobot {

    private List<Locker> lockers;

    public PrimaryLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket storeBag(Bag bag) {
        Optional<Locker> lockerOptional = lockers.stream().filter(locker -> !locker.isFull()).findFirst();
        return lockerOptional.orElseThrow(CapacityFullException::new).storeBag(bag);
    }

    public Bag takeOutBag(Ticket ticket) {
        AtomicReference<Bag> takeOutBag = new AtomicReference<>();
        lockers.forEach(locker -> {
            Optional<Bag> bagOptional = locker.takeOutBag(ticket);
            bagOptional.ifPresent(takeOutBag::set);
        });
        return takeOutBag.get();
    }
}
