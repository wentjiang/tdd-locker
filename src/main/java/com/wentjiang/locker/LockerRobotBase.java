package com.wentjiang.locker;

import com.wentjiang.locker.exception.BadTicketException;

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
        return bagOperates.stream().mapToInt(BagOperate::getFreeCapacity).sum();
    }

    @Override
    public int getCapacity() {
        return bagOperates.stream().mapToInt(BagOperate::getCapacity).sum();
    }

    @Override
    public String statisticalForm(int layer) {
        int freeCapacity = getFreeCapacity();
        int capacity = getCapacity();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < layer; i++) {
            result.append(Constants.EVERY_LAYER_BLACK_STRING);
        }
        result.append("R ").append(freeCapacity).append(" ").append(capacity).append("\n");
        for (BagOperate bagOperate : bagOperates) {
            result.append(bagOperate.statisticalForm(layer + 1));
        }
        return result.toString();
    }
}
