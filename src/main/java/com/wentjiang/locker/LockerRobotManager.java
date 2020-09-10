package com.wentjiang.locker;

import com.wentjiang.locker.exception.BadTicketException;
import com.wentjiang.locker.exception.CapacityFullException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LockerRobotManager implements BagOperate {

    private final List<BagOperate> bagOperates = new ArrayList<>();

    public LockerRobotManager(List<Locker> lockers, List<LockerRobotBase> robots) {
        bagOperates.addAll(robots);
        bagOperates.addAll(lockers);
    }

    @Override
    public Ticket storeBag(Bag bag) {
        Optional<BagOperate> bagOperateOptional = bagOperates.stream().filter(BagOperate::isNotFull).findFirst();
        return bagOperateOptional.orElseThrow(CapacityFullException::new).storeBag(bag);
    }

    @Override
    public boolean isNotFull() {
        return bagOperates.stream().anyMatch(BagOperate::isNotFull);
    }

    @Override
    public int getFreeCapacity() {
        throw new NotImplementedException();
    }

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
}
