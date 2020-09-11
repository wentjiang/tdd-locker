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
        return bagOperates.stream().mapToInt(BagOperate::getFreeCapacity).sum();
    }

    @Override
    public int getCapacity() {
        return bagOperates.stream().mapToInt(BagOperate::getCapacity).sum();
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

    @Override
    public String statisticalForm(int layer) {
        int freeCapacity = this.getFreeCapacity();
        int capacity = this.getCapacity();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < layer; i++) {
            result.append(Constants.EVERY_LAYER_BLACK_STRING);
        }
        result.append("M ").append(freeCapacity).append(" ").append(capacity).append("\n");
        for (BagOperate bagOperate : bagOperates) {
            result.append(bagOperate.statisticalForm(layer + 1));
        }
        return result.toString();
    }
}
