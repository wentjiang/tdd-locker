package com.wentjiang.locker;

import com.wentjiang.locker.exception.BadTicketException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public abstract class LockerRobotBase implements BagOperate {

    protected final List<BagOperate> bagOperates = new ArrayList<>();

    public LockerRobotBase(List<Locker> lockers) {
        this.bagOperates.addAll(lockers);
    }

    @Override
    public abstract Ticket storeBag(Bag bag);

    @Override
    public Bag takeOutBag(Ticket ticket) {
        for (BagOperate bagOperate : bagOperates) {
            try {
                return bagOperate.takeOutBag(ticket);
            } catch (BadTicketException ignored) {
            }
        }
        throw new BadTicketException();
    }

    @Override
    public boolean isNotFull() {
        return bagOperates.stream().anyMatch(BagOperate::isNotFull);
    }

    @Override
    public int getFreeCapacity() {
        throw new NotImplementedException();
    }
}
