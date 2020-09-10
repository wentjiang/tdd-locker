package com.wentjiang.locker;


import com.wentjiang.locker.exception.BadTicketException;
import com.wentjiang.locker.exception.CapacityFullException;
import com.wentjiang.locker.exception.TicketUsedException;

import java.util.*;

public class Locker implements BagOperate {

    private final int capacity;
    private final Map<Ticket, Bag> ticketMap = new HashMap<>();
    private final Set<Ticket> usedTicketSet = new HashSet<>();

    public Locker(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public Ticket storeBag(Bag bag) {
        if (ticketMap.size() == capacity) {
            throw new CapacityFullException();
        }
        Ticket ticket = new Ticket();
        ticketMap.put(ticket, bag);
        return ticket;
    }

    @Override
    public boolean isNotFull() {
        return capacity > ticketMap.size();
    }

    @Override
    public Bag takeOutBag(Ticket ticket) {
        if (usedTicketSet.contains(ticket)) {
            throw new TicketUsedException();
        }
        Bag bag = ticketMap.get(ticket);
        if (bag != null) {
            usedTicketSet.add(ticket);
            ticketMap.remove(ticket);
            return bag;
        } else {
            throw new BadTicketException();
        }
    }

    @Override
    public int getFreeCapacity() {
        return capacity - ticketMap.size();
    }
}
