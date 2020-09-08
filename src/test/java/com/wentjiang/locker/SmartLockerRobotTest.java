package com.wentjiang.locker;

import com.wentjiang.locker.exception.BadTicketException;
import com.wentjiang.locker.exception.CapacityFullException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

public class SmartLockerRobotTest {

    @Test
    public void should_store_bag_success_when_store_bag_given_SmartLockerRobot_manager_two_not_full_locker_and_bag() {
        Locker emptyLocker1 = LockerUtil.getEmptyLocker();
        Locker emptyLocker2 = LockerUtil.getEmptyLocker();
        SmartLockerRobot robot = new SmartLockerRobot(Arrays.asList(emptyLocker1, emptyLocker2));
        Bag bag = new Bag();
        Ticket ticket = robot.storeBag(bag);
        Assertions.assertNotNull(ticket);
    }

    @Test
    public void should_store_bag_in_less_locker_when_store_bag_given_smartLockerRobot_manger_two_lockers_that_second_free_space_more_than_first() {
        int firstLockerStoreNum = 5;
        int secondLockerStoreNum = 4;
        Locker locker1 = LockerUtil.getStoredLocker(firstLockerStoreNum, LockerUtil.DEFAULT_CAPACITY);
        Locker locker2 = LockerUtil.getStoredLocker(secondLockerStoreNum, LockerUtil.DEFAULT_CAPACITY);
        SmartLockerRobot robot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        Bag bag = new Bag();
        Ticket ticket = robot.storeBag(bag);
        Assertions.assertEquals(bag, locker2.takeOutBag(ticket));
    }

    @Test
    public void should_store_bag_in_first_when_store_bag_given_smartLockerRobot_manager_two_same_free_capacity_locker() {
        int firstLockerStoreNum = 5;
        int secondLockerStoreNum = 5;
        Locker locker1 = LockerUtil.getStoredLocker(firstLockerStoreNum, LockerUtil.DEFAULT_CAPACITY);
        Locker locker2 = LockerUtil.getStoredLocker(secondLockerStoreNum, LockerUtil.DEFAULT_CAPACITY);
        SmartLockerRobot robot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        Bag bag = new Bag();
        Ticket ticket = robot.storeBag(bag);
        Assertions.assertEquals(bag, locker1.takeOutBag(ticket));
    }

    @Test
    public void should_store_bag_fail_and_remind_lockers_is_full_when_store_bag_given_smartLockerRobot_manager_two_full_lockers() {
        Locker fullLocker1 = LockerUtil.getFullLocker();
        Locker fullLocker2 = LockerUtil.getFullLocker();
        SmartLockerRobot robot = new SmartLockerRobot(Arrays.asList(fullLocker1, fullLocker2));
        Assertions.assertThrows(CapacityFullException.class, () -> robot.storeBag(new Bag()));
    }

    @Test
    public void should_take_out_bag_currently_when_take_out_bag_given_martLockerRobot_manager_two_lockers() {
        SmartLockerRobot robot = new SmartLockerRobot(Arrays.asList(LockerUtil.getEmptyLocker(), LockerUtil.getEmptyLocker()));
        Bag bag = new Bag();
        Ticket ticket = robot.storeBag(bag);
        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(bag, robot.takeOutBag(ticket));
    }

    @Test
    public void should_take_out_bag_fail_remind_invalid_ticket_when_take_out_bag_given_invalid_ticket() {
        SmartLockerRobot robot = new SmartLockerRobot(Arrays.asList(LockerUtil.getEmptyLocker(), LockerUtil.getEmptyLocker()));
        Bag bag = new Bag();
        Ticket validTicket = robot.storeBag(bag);
        Ticket invalidTicket = new Ticket();
        Assertions.assertThrows(BadTicketException.class, () -> robot.takeOutBag(invalidTicket));
    }

}
