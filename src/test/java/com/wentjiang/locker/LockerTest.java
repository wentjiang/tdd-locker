package com.wentjiang.locker;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LockerTest {

    private static int DEFAULT_CAPACITY = 10;
    ;

    @Test
    public void test() {
        System.out.println("hello test");
    }

    @Test
    public void should_generate_ticket_WHEN_store_bag_GIVEN_locker_not_full_and_bag() {
        Locker locker = new Locker(DEFAULT_CAPACITY);
        Bag bag = new Bag("test name");
        Ticket ticket = locker.storeBag(bag);
        Assert.assertNotNull(ticket);
    }

    @Test
    public void should_remind_locker_is_full_WHEN_store_bag_GIVEN_full_locker_and_bag() {
        Locker locker = new Locker(DEFAULT_CAPACITY);
        for (int i = 0; i < 10; i++) {
            locker.storeBag(new Bag("test name " + (i + 1)));
        }
        Bag bag = new Bag("test name " + 11);
        CapacityFullException thrown =
                Assertions.assertThrows(CapacityFullException.class, () -> locker.storeBag(bag));
        Assertions.assertTrue(thrown.getMessage().contains("the locker is full"));
    }

}
