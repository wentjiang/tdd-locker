package com.wentjiang.locker;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class LockerTest {

    @Test
    public void test() {
        System.out.println("hello test");
    }

    @Test
    public void should_generate_ticket_when_locker_not_full() {
        Locker locker = new Locker(10);
        Bag bag = new Bag("test name");
        Ticket ticket = locker.storeBag(bag);
        Assert.assertNotNull(ticket);
    }

}
