package com.wentjiang.locker;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Locker {

    private int capacity;
    private int currentCapacity = 0;
    private Map<String, Ticket> ticketMap = new HashMap<>();
    private Map<String, Ticket> usedTicketMap = new HashMap<>();

    public Locker(int capacity) {
        this.capacity = capacity;
    }

    public static void main(String[] args) {
        System.out.println("hello world");
    }

    public Ticket storeBag() {
        if (currentCapacity == capacity) {
            throw new CapacityFullException(CapacityFullException.ERROR_MESSAGE_CAPACITY_FULL);
        }
        currentCapacity++;
        Ticket ticket = new Ticket(UUID.randomUUID().toString());
        ticketMap.put(ticket.getId(), ticket);
        return ticket;
    }

    public boolean takeOutBag(Ticket ticket) {
        if (usedTicketMap.containsKey(ticket.getId())) {
            throw new TicketException(TicketException.ERROR_MESSAGE_USED_TICKET);
        }
        Ticket verifyTicket = ticketMap.get(ticket.getId());
        if (verifyTicket == null) {
            throw new TicketException(TicketException.ERROR_MESSAGE_BAD_TICKET);
        }
        usedTicketMap.put(verifyTicket.getId(), verifyTicket);
        ticketMap.remove(verifyTicket.getId());
        return true;
    }
}
