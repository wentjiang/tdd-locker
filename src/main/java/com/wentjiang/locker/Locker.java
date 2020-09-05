package com.wentjiang.locker;


import com.wentjiang.locker.exception.BadTicketException;
import com.wentjiang.locker.exception.CapacityFullException;
import com.wentjiang.locker.exception.TicketUsedException;

import java.util.*;

public class Locker {

    private final int capacity;
    private int usedCapacity = 0;
    private final Map<Ticket, Bag> ticketMap = new HashMap<>();
    private final Set<Ticket> usedTicketSet = new HashSet<>();

    public Locker(int capacity) {
        this.capacity = capacity;
    }

    public Ticket storeBag(Bag bag) {
        if (usedCapacity == capacity) {
            throw new CapacityFullException();
        }
        usedCapacity++;
        Ticket ticket = new Ticket(UUID.randomUUID().toString());
        ticketMap.put(ticket, bag);
        return ticket;
    }

    public Optional<Bag> takeOutBag(Ticket ticket) {
        if (usedTicketSet.contains(ticket)) {
            throw new TicketUsedException();
        }
        Bag bag = ticketMap.get(ticket);
        if (bag != null) {
            usedTicketSet.add(ticket);
            ticketMap.remove(ticket);
        }
        return Optional.ofNullable(bag);
    }

    public boolean isFull() {
        return usedCapacity == capacity;
    }

    public int getFreeCapacity() {
        return capacity - usedCapacity;
    }
}
