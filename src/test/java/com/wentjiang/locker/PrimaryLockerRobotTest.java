package com.wentjiang.locker;

import com.wentjiang.locker.exception.BadTicketException;
import com.wentjiang.locker.exception.CapacityFullException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PrimaryLockerRobotTest {

    @Test
    public void should_store_bag_and_return_ticket_when_PrimaryLockerRobot_store_bag_given_PrimaryLockerRobot_manager_not_full_locker() {
        List<Locker> lockers = new ArrayList<>();
        lockers.add(LockerUtil.emptyLocker1);
        lockers.add(LockerUtil.emptyLocker2);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(lockers);
        Bag bag = new Bag();
        Ticket ticket = primaryLockerRobot.storeBag(bag);
        Assertions.assertNotNull(ticket);
        Optional<Bag> bagOptional = LockerUtil.emptyLocker1.takeOutBag(ticket);
        Assertions.assertTrue(bagOptional.isPresent());
        Assertions.assertEquals(bag, bagOptional.get());
    }

    @Test
    public void should_store_bag_and_return_ticket_when_PrimaryLockerRobot_store_bag_given_PrimaryLockerRobot_manager_first_full_locker_and_second_not_full() {
        List<Locker> lockers = new ArrayList<>();
        lockers.add(LockerUtil.fullLocker1);
        lockers.add(LockerUtil.emptyLocker1);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(lockers);
        Bag bag = new Bag();
        Ticket ticket = primaryLockerRobot.storeBag(bag);
        Assertions.assertNotNull(ticket);
        Optional<Bag> bagOptional = LockerUtil.emptyLocker1.takeOutBag(ticket);
        Assertions.assertTrue(bagOptional.isPresent());
        Assertions.assertEquals(bag, bagOptional.get());
    }

    @Test
    public void should_not_store_bag_and_remind_locker_is_full_when_PrimaryLockerRobot_store_bag_given_PrimaryLockerRobot_manager_full_locker() {
        List<Locker> lockers = new ArrayList<>();
        lockers.add(LockerUtil.fullLocker1);
        lockers.add(LockerUtil.fullLocker2);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(lockers);
        Assertions.assertThrows(CapacityFullException.class, () -> primaryLockerRobot.storeBag(new Bag()));
    }

    @Test
    public void should_take_out_bag_currently_when_PrimaryLockerRobot_take_out_bag_given_valid_ticket() {
        List<Locker> lockers = new ArrayList<>();
        lockers.add(LockerUtil.emptyLocker1);
        lockers.add(LockerUtil.emptyLocker2);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(lockers);
        Bag bag = new Bag();
        Ticket ticket = primaryLockerRobot.storeBag(bag);
        Assertions.assertEquals(bag, primaryLockerRobot.takeOutBag(ticket));
    }

    @Test
    public void should_remind_bad_ticket_when_PrimaryLockerRobot_take_out_bag_given_invalid_ticket() {
        List<Locker> lockers = new ArrayList<>();
        lockers.add(LockerUtil.emptyLocker1);
        lockers.add(LockerUtil.emptyLocker2);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(lockers);
        Ticket validTicket = primaryLockerRobot.storeBag(new Bag());
        Ticket invalidTicket = new Ticket(UUID.randomUUID().toString());
        Assertions.assertThrows(BadTicketException.class, () -> primaryLockerRobot.takeOutBag(invalidTicket));
    }
}
