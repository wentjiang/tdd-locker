package com.wentjiang.locker;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LockerRobotManagerTest {

    @Test
    public void should_store_in_first_locker_when_locker_Robot_Manager_store_bag_given_two_not_full_lockers_and_no_robot() {
        List<Locker> lockers = Arrays.asList(LockerUtil.getEmptyLocker(), LockerUtil.getEmptyLocker());
        List<LockerRobotBase> robots = Collections.emptyList();
        LockerRobotManager lockerRobotManager = new LockerRobotManager(lockers, robots);
        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.storeBag(bag);
        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(bag, lockers.get(0).takeOutBag(ticket).get());
    }

    @Test
    public void should_store_in_second_locker_when_locker_Robot_Manager_store_bag_given_first_full_locker_second_not_full_and_no_robot() {
        List<Locker> lockers = Arrays.asList(LockerUtil.getFullLocker(), LockerUtil.getEmptyLocker());
        List<LockerRobotBase> robots = Collections.emptyList();
        LockerRobotManager lockerRobotManager = new LockerRobotManager(lockers, robots);
        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.storeBag(bag);
        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(bag, lockers.get(1).takeOutBag(ticket).get());
    }

}
