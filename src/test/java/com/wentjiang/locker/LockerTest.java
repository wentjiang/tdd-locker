package com.wentjiang.locker;

import com.wentjiang.locker.exception.BadTicketException;
import com.wentjiang.locker.exception.CapacityFullException;
import com.wentjiang.locker.exception.TicketUsedException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LockerTest {

    private static final int DEFAULT_CAPACITY = 10;
    private Locker locker;

    @BeforeEach
    public void init() {
        locker = new Locker(DEFAULT_CAPACITY);
    }

    @Test
    public void should_generate_ticket_WHEN_store_bag_GIVEN_locker_not_full_and_bag() {
        Ticket ticket = locker.storeBag(new Bag());
        Assert.assertNotNull(ticket);
    }

    @Test
    public void should_remind_locker_is_full_WHEN_store_bag_GIVEN_full_locker_and_bag() {
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            locker.storeBag(new Bag());
        }
        Assertions.assertThrows(CapacityFullException.class, () -> locker.storeBag(new Bag()));
    }

    @Test
    public void should_verify_passed_WHEN_take_bag_GIVEN_correct_ticket() {
        Bag bag = new Bag();
        Ticket ticket = locker.storeBag(bag);
        Assertions.assertEquals(bag, locker.takeOutBag(ticket));
    }

    @Test
    public void should_return_nothing_WHEN_take_bag_GIVEN_bad_ticket() {
        Ticket badTicket = new Ticket();
        Assertions.assertThrows(BadTicketException.class, () -> locker.takeOutBag(badTicket));
    }

    @Test
    public void should_verify_not_passed_WHEN_take_bag_GIVEN_used_ticket() {
        Bag bag = new Bag();
        Ticket ticket = locker.storeBag(bag);
        Assertions.assertEquals(bag, locker.takeOutBag(ticket));
        Assertions.assertThrows(TicketUsedException.class, () -> locker.takeOutBag(ticket));
    }

}
