package com.wentjiang.locker;

import com.wentjiang.locker.exception.BadTicketException;
import com.wentjiang.locker.exception.CapacityFullException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;

public class PrimaryLockerRobotTest {

    @Test
    public void should_store_bag_and_return_ticket_when_PrimaryLockerRobot_store_bag_given_PrimaryLockerRobot_manager_not_full_locker() {
        Locker emptyLocker1 = LockerUtil.getEmptyLocker();
        Locker emptyLocker2 = LockerUtil.getEmptyLocker();
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(emptyLocker1, emptyLocker2));
        Bag bag = new Bag();
        Ticket ticket = primaryLockerRobot.storeBag(bag);
        Assertions.assertNotNull(ticket);
    }

    @Test
    public void should_store_bag_and_return_ticket_when_PrimaryLockerRobot_store_bag_given_PrimaryLockerRobot_manager_first_full_locker_and_second_not_full() {
        Locker emptyLocker1 = LockerUtil.getEmptyLocker();
        Locker emptyLocker2 = LockerUtil.getEmptyLocker();
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(emptyLocker1, emptyLocker2));
        Bag bag = new Bag();
        Ticket ticket = primaryLockerRobot.storeBag(bag);
        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(bag, emptyLocker1.takeOutBag(ticket));
    }

    @Test
    public void should_not_store_bag_and_remind_locker_is_full_when_PrimaryLockerRobot_store_bag_given_PrimaryLockerRobot_manager_full_locker() {
        Locker fullLocker1 = LockerUtil.getFullLocker();
        Locker fullLocker2 = LockerUtil.getFullLocker();
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(fullLocker1, fullLocker2));
        Assertions.assertThrows(CapacityFullException.class, () -> primaryLockerRobot.storeBag(new Bag()));
    }

    @Test
    public void should_take_out_bag_currently_when_PrimaryLockerRobot_take_out_bag_given_valid_ticket() {
        Locker emptyLocker1 = LockerUtil.getEmptyLocker();
        Locker emptyLocker2 = LockerUtil.getEmptyLocker();
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(emptyLocker1, emptyLocker2));
        Bag bag = new Bag();
        Ticket ticket = primaryLockerRobot.storeBag(bag);
        Assertions.assertEquals(bag, primaryLockerRobot.takeOutBag(ticket));
    }

    @Test
    public void should_remind_bad_ticket_when_PrimaryLockerRobot_take_out_bag_given_invalid_ticket() {
        Locker emptyLocker1 = LockerUtil.getEmptyLocker();
        Locker emptyLocker2 = LockerUtil.getEmptyLocker();
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(emptyLocker1, emptyLocker2));
        Ticket validTicket = primaryLockerRobot.storeBag(new Bag());
        Ticket invalidTicket = new Ticket();
        Assertions.assertThrows(BadTicketException.class, () -> primaryLockerRobot.takeOutBag(invalidTicket));
    }
}
