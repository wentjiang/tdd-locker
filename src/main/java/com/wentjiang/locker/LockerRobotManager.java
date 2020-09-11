package com.wentjiang.locker;

import com.wentjiang.locker.exception.BadTicketException;
import com.wentjiang.locker.exception.CapacityFullException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LockerRobotManager implements BagOperate {

    private final List<BagOperate> robots = new ArrayList<>();
    private final List<BagOperate> lockers = new ArrayList<>();

    public LockerRobotManager(List<Locker> lockers, List<LockerRobotBase> robots) {
        this.robots.addAll(robots);
        this.lockers.addAll(lockers);
    }

    @Override
    public Ticket storeBag(Bag bag) {
        Optional<BagOperate> bagOperateOptional = robots.stream().filter(BagOperate::isNotFull).findFirst();
        if (!bagOperateOptional.isPresent()) {
            bagOperateOptional = lockers.stream().filter(BagOperate::isNotFull).findFirst();
        }
        return bagOperateOptional.orElseThrow(CapacityFullException::new).storeBag(bag);
    }

    @Override
    public boolean isNotFull() {
        return robots.stream().anyMatch(BagOperate::isNotFull)
                || lockers.stream().anyMatch(BagOperate::isNotFull);
    }

    @Override
    public int getFreeCapacity() {
        return robots.stream().mapToInt(BagOperate::getFreeCapacity).sum()
                + lockers.stream().mapToInt(BagOperate::getFreeCapacity).sum();
    }

    @Override
    public int getCapacity() {
        return robots.stream().mapToInt(BagOperate::getCapacity).sum()
                + lockers.stream().mapToInt(BagOperate::getCapacity).sum();
    }

    @Override
    public Bag takeOutBag(Ticket ticket) {
        for (BagOperate bagOperate : robots) {
            try {
                return bagOperate.takeOutBag(ticket);
            } catch (BadTicketException ignored) {
            }
        }
        for (BagOperate bagOperate : lockers) {
            try {
                return bagOperate.takeOutBag(ticket);
            } catch (BadTicketException ignored) {
            }
        }
        throw new BadTicketException();
    }

    @Override
    public String statisticalForm(int layer) {
        int freeCapacity = getFreeCapacity();
        int capacity = getCapacity();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < layer; i++) {
            result.append(Constants.EVERY_LAYER_BLACK_STRING);
        }
        result.append("M ").append(freeCapacity).append(" ").append(capacity).append("\n");
        for (BagOperate bagOperate : lockers) {
            result.append(bagOperate.statisticalForm(layer + 1));
        }
        for (BagOperate bagOperate : robots) {
            result.append(bagOperate.statisticalForm(layer + 1));
        }
        return result.toString();
    }
}
