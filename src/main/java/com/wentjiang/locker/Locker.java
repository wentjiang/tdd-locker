package com.wentjiang.locker;


import java.util.UUID;

public class Locker {

    private int capacity;
    private int currentCapacity = 0;

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
            throw new CapacityFullException("the locker is full");
        }
        currentCapacity++;
        return new Ticket(UUID.randomUUID().toString());
    }

    public boolean takeOut(Ticket ticket) {
        return true;
    }
}
