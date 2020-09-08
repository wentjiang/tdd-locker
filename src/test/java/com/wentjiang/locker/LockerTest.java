package com.wentjiang.locker;

import com.wentjiang.locker.exception.BadTicketException;
import com.wentjiang.locker.exception.CapacityFullException;
import com.wentjiang.locker.exception.TicketUsedException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

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
        Ticket ticket = locker.storeBag(new Bag());
        Optional<Bag> bagOptional = locker.takeOutBag(ticket);
        Assertions.assertTrue(bagOptional.isPresent());
    }

    @Test
    public void should_return_nothing_WHEN_take_bag_GIVEN_bad_ticket() {

        Ticket badTicket = new Ticket();

        Assertions.assertFalse(locker.takeOutBag(badTicket).isPresent());
    }

    //given locker,已经被验证过的小票 when 取包 then 验证失败,提示票无效
    @Test
    public void should_verify_not_passed_WHEN_take_bag_GIVEN_used_ticket() {
        Ticket ticket = locker.storeBag(new Bag());

        Optional<Bag> bagOptional = locker.takeOutBag(ticket);

        Assertions.assertTrue(bagOptional.isPresent());
        Assertions.assertThrows(TicketUsedException.class, () -> locker.takeOutBag(ticket));
    }

}
