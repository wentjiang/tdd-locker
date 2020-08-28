package com.wentjiang.locker;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Locker {

    private int capacity;
    private int currentCapacity = 0;
    private Map<String, Ticket> ticketMap = new HashMap<>();

    public Locker(int capacity) {
        this.capacity = capacity;
    }

    public static void main(String[] args) {
        System.out.println("hello world");
    }

    public int getCapacity() {
        return capacity;
    }

    public Ticket storeBag(Bag bag) {
        if (currentCapacity == capacity) {
            throw new CapacityFullException(CapacityFullException.ERROR_MESSAGE_CAPACITY_FULL);
        }
        currentCapacity++;
        Ticket ticket = new Ticket(UUID.randomUUID().toString());
        ticketMap.put(ticket.getId(), ticket);
        return ticket;
    }

    public boolean takeOut(Ticket ticket) {
        Ticket verifyTicket = ticketMap.get(ticket.getId());
        if (verifyTicket == null) {
            throw new TicketException(TicketException.ERROR_MESSAGE_TICKET);
        }
        return true;
    }
}
