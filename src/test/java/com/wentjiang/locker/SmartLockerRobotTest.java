package com.wentjiang.locker;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Optional;

public class SmartLockerRobotTest {

    @Test
    public void should_store_bag_success_when_store_bag_given_SmartLockerRobot_manager_two_not_full_locker_and_bag() {
        SmartLockerRobot robot = new SmartLockerRobot(Arrays.asList(LockerUtil.emptyLocker1, LockerUtil.emptyLocker2));
        Bag bag = new Bag();
        Ticket ticket = robot.storeBag(bag);
        Optional<Bag> bagOptional = LockerUtil.emptyLocker1.takeOutBag(ticket);
        Assertions.assertTrue(bagOptional.isPresent());
        Assertions.assertEquals(bag, bagOptional.get());
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
        Optional<Bag> bagOptional = locker2.takeOutBag(ticket);
        Assertions.assertTrue(bagOptional.isPresent());
        Assertions.assertEquals(bag, bagOptional.get());
    }

}
