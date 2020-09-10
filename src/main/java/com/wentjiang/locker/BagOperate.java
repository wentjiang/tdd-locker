package com.wentjiang.locker;

public interface BagOperate {

    Bag takeOutBag(Ticket ticket);

    Ticket storeBag(Bag bag);

    boolean isNotFull();

    int getFreeCapacity();
}
