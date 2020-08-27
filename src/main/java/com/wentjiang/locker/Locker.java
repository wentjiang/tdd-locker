package com.wentjiang.locker;


import java.util.UUID;

public class Locker {

    private int capacity;

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
        return new Ticket(UUID.randomUUID().toString());
    }
}
