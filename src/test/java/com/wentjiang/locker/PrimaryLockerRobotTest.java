package com.wentjiang.locker;

import com.wentjiang.locker.exception.BadTicketException;
import com.wentjiang.locker.exception.CapacityFullException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PrimaryLockerRobotTest {

    private static final int DEFAULT_CAPACITY = 10;
    private static final Locker emptyLocker1;
    private static final Locker emptyLocker2;
    private static final Locker fullLocker1;
    private static final Locker fullLocker2;

    static {
        emptyLocker1 = new Locker(DEFAULT_CAPACITY);
        emptyLocker2 = new Locker(DEFAULT_CAPACITY);
        fullLocker1 = new Locker(DEFAULT_CAPACITY);
        fullLocker2 = new Locker(DEFAULT_CAPACITY);
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            fullLocker1.storeBag(new Bag());
            fullLocker2.storeBag(new Bag());
        }
    }


    @Test
    public void should_store_bag_and_return_ticket_when_PrimaryLockerRobot_store_bag_given_PrimaryLockerRobot_manager_not_full_locker() {
        List<Locker> lockers = new ArrayList<>();
        lockers.add(emptyLocker1);
        lockers.add(emptyLocker2);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(lockers);
        Ticket ticket = primaryLockerRobot.storeBag(new Bag());
        Assertions.assertNotNull(ticket);
    }

    @Test
    public void should_store_bag_and_return_ticket_when_PrimaryLockerRobot_store_bag_given_PrimaryLockerRobot_manager_first_full_locker_and_second_not_full() {
        List<Locker> lockers = new ArrayList<>();
        lockers.add(fullLocker1);
        lockers.add(emptyLocker1);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(lockers);
        Ticket ticket = primaryLockerRobot.storeBag(new Bag());
        Assertions.assertNotNull(ticket);
    }

    @Test
    public void should_not_store_bag_and_remind_locker_is_full_when_PrimaryLockerRobot_store_bag_given_PrimaryLockerRobot_manager_full_locker() {
        List<Locker> lockers = new ArrayList<>();
        lockers.add(fullLocker1);
        lockers.add(fullLocker2);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(lockers);
        Assertions.assertThrows(CapacityFullException.class, () -> primaryLockerRobot.storeBag(new Bag()));
    }

    @Test
    public void should_take_out_bag_currently_when_PrimaryLockerRobot_take_out_bag_given_valid_ticket() {
        List<Locker> lockers = new ArrayList<>();
        lockers.add(emptyLocker1);
        lockers.add(emptyLocker2);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(lockers);
        Ticket ticket = primaryLockerRobot.storeBag(new Bag());
        Bag bag = primaryLockerRobot.takeOutBag(ticket);
        Assertions.assertNotNull(bag);
    }

    @Test
    public void should_remind_bad_ticket_when_PrimaryLockerRobot_take_out_bag_given_invalid_ticket() {
        List<Locker> lockers = new ArrayList<>();
        lockers.add(emptyLocker1);
        lockers.add(emptyLocker2);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(lockers);
        Ticket validTicket = primaryLockerRobot.storeBag(new Bag());
        Ticket invalidTicket = new Ticket(UUID.randomUUID().toString());
        Assertions.assertThrows(BadTicketException.class, () -> primaryLockerRobot.takeOutBag(invalidTicket));
    }
}
