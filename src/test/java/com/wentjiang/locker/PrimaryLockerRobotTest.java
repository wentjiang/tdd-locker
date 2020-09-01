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

    @Test
    public void should_store_bag_and_return_ticket_when_PrimaryLockerRobot_store_bag_given_PrimaryLockerRobot_manager_not_full_locker() {
        List<Locker> lockers = new ArrayList<>();
        lockers.add(new Locker(DEFAULT_CAPACITY));
        lockers.add(new Locker(DEFAULT_CAPACITY));
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(lockers);
        Ticket ticket = primaryLockerRobot.storeBag(new Bag());
        Assertions.assertNotNull(ticket);
    }

    @Test
    public void should_store_bag_and_return_ticket_when_PrimaryLockerRobot_store_bag_given_PrimaryLockerRobot_manager_first_full_locker_and_second_not_full() {
        List<Locker> lockers = new ArrayList<>();
        Locker fullLocker = new Locker(DEFAULT_CAPACITY);
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            fullLocker.storeBag(new Bag());
        }
        Locker notFullLocker = new Locker(DEFAULT_CAPACITY);
        lockers.add(fullLocker);
        lockers.add(notFullLocker);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(lockers);
        Ticket ticket = primaryLockerRobot.storeBag(new Bag());
        Assertions.assertNotNull(ticket);
    }

    @Test
    public void should_not_store_bag_and_remind_locker_is_full_when_PrimaryLockerRobot_store_bag_given_PrimaryLockerRobot_manager_full_locker() {
        List<Locker> lockers = new ArrayList<>();
        Locker fullLocker1 = new Locker(DEFAULT_CAPACITY);
        Locker fullLocker2 = new Locker(DEFAULT_CAPACITY);
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            fullLocker1.storeBag(new Bag());
            fullLocker2.storeBag(new Bag());
        }
        lockers.add(fullLocker1);
        lockers.add(fullLocker2);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(lockers);
        Assertions.assertThrows(CapacityFullException.class, () -> primaryLockerRobot.storeBag(new Bag()));
    }

    @Test
    public void should_take_out_bag_currently_when_PrimaryLockerRobot_take_out_bag_given_valid_ticket() {
        List<Locker> lockers = new ArrayList<>();
        lockers.add(new Locker(DEFAULT_CAPACITY));
        lockers.add(new Locker(DEFAULT_CAPACITY));
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(lockers);
        Ticket ticket = primaryLockerRobot.storeBag(new Bag());
        Bag bag = primaryLockerRobot.takeOutBag(ticket);
        Assertions.assertNotNull(bag);
    }

    //- given PrimaryLockerRobot管理多个Locker，无效小票 when PrimaryLockerRobot取包 then 取包失败，Robot提示无效票据
    @Test
    public void should_remind_bad_ticket_when_PrimaryLockerRobot_take_out_bag_given_invalid_ticket() {
        List<Locker> lockers = new ArrayList<>();
        lockers.add(new Locker(DEFAULT_CAPACITY));
        lockers.add(new Locker(DEFAULT_CAPACITY));
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(lockers);
        Ticket validTicket = primaryLockerRobot.storeBag(new Bag());
        Ticket invalidTicket = new Ticket(UUID.randomUUID().toString());
        Assertions.assertThrows(BadTicketException.class, () -> primaryLockerRobot.takeOutBag(invalidTicket));
    }
}
