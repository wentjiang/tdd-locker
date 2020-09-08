package com.wentjiang.locker;

import com.wentjiang.locker.exception.BadTicketException;
import com.wentjiang.locker.exception.CapacityFullException;
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
        Assertions.assertEquals(bag, lockers.get(0).takeOutBag(ticket));
    }

    @Test
    public void should_store_in_second_locker_when_locker_Robot_Manager_store_bag_given_first_full_locker_second_not_full_and_no_robot() {
        List<Locker> lockers = Arrays.asList(LockerUtil.getFullLocker(), LockerUtil.getEmptyLocker());
        List<LockerRobotBase> robots = Collections.emptyList();
        LockerRobotManager lockerRobotManager = new LockerRobotManager(lockers, robots);
        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.storeBag(bag);
        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(bag, lockers.get(1).takeOutBag(ticket));
    }

    @Test
    public void should_store_fail_when_locker_robot_manager_store_bag_given_two_full_lockers_and_no_robot() {
        List<Locker> lockers = Arrays.asList(LockerUtil.getFullLocker(), LockerUtil.getFullLocker());
        List<LockerRobotBase> robots = Collections.emptyList();
        LockerRobotManager lockerRobotManager = new LockerRobotManager(lockers, robots);
        Assertions.assertThrows(CapacityFullException.class, () -> lockerRobotManager.storeBag(new Bag()));
    }

    @Test
    public void should_store_in_first_robot_when_locker_robot_manager_store_bag_given_two_not_full_robot_and_no_lockers() {
        List<Locker> lockers = Collections.emptyList();
        List<LockerRobotBase> robots = Arrays.asList(LockerUtil.getNotFullPrimaryLockerRobot(), LockerUtil.getNotFullPrimaryLockerRobot());
        LockerRobotManager lockerRobotManager = new LockerRobotManager(lockers, robots);
        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.storeBag(bag);
        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(bag, robots.get(0).takeOutBag(ticket));
    }

    @Test
    public void should_store_in_second_robot_when_locker_robot_manager_store_bag_given_first_full_robot_second_not_full_robot_and_no_lockers() {
        List<Locker> lockers = Collections.emptyList();
        List<LockerRobotBase> robots = Arrays.asList(LockerUtil.getFullPrimaryLockerRobot(), LockerUtil.getNotFullPrimaryLockerRobot());
        LockerRobotManager lockerRobotManager = new LockerRobotManager(lockers, robots);
        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.storeBag(bag);
        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(bag, robots.get(1).takeOutBag(ticket));
    }

    @Test
    public void should_store_fail_when_locker_robot_manager_store_bag_given_two_full_robots_and_no_lockers() {
        List<Locker> lockers = Collections.emptyList();
        List<LockerRobotBase> robots = Arrays.asList(LockerUtil.getFullPrimaryLockerRobot(), LockerUtil.getFullPrimaryLockerRobot());
        LockerRobotManager lockerRobotManager = new LockerRobotManager(lockers, robots);
        Assertions.assertThrows(CapacityFullException.class, () -> lockerRobotManager.storeBag(new Bag()));
    }

    @Test
    public void should_store_in_robot_when_locker_robot_manager_store_bag_given_not_full_robot_and_not_full_locker() {
        List<Locker> lockers = Collections.singletonList(LockerUtil.getEmptyLocker());
        List<LockerRobotBase> robots = Collections.singletonList(LockerUtil.getNotFullPrimaryLockerRobot());
        LockerRobotManager lockerRobotManager = new LockerRobotManager(lockers, robots);
        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.storeBag(bag);
        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(bag, robots.get(0).takeOutBag(ticket));
    }

    @Test
    public void should_store_in_robot_when_locker_robot_manager_store_bag_given_full_robot_and_not_full_locker() {
        List<Locker> lockers = Collections.singletonList(LockerUtil.getEmptyLocker());
        List<LockerRobotBase> robots = Collections.singletonList(LockerUtil.getFullPrimaryLockerRobot());
        LockerRobotManager lockerRobotManager = new LockerRobotManager(lockers, robots);
        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.storeBag(bag);
        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(bag, lockers.get(0).takeOutBag(ticket));
    }

    @Test
    public void should_store_in_robot_when_locker_robot_manager_store_bag_given_full_robot_and_full_locker() {
        List<Locker> lockers = Collections.singletonList(LockerUtil.getFullLocker());
        List<LockerRobotBase> robots = Collections.singletonList(LockerUtil.getFullPrimaryLockerRobot());
        LockerRobotManager lockerRobotManager = new LockerRobotManager(lockers, robots);
        Assertions.assertThrows(CapacityFullException.class, () -> lockerRobotManager.storeBag(new Bag()));
    }

    @Test
    public void should_take_out_successfully_when_manager_take_out_bag_given_manager_two_lockers_and_no_robot_and_valid_ticket() {
        List<Locker> lockers = Arrays.asList(LockerUtil.getEmptyLocker(), LockerUtil.getEmptyLocker());
        LockerRobotManager lockerRobotManager = new LockerRobotManager(lockers, Collections.emptyList());
        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.storeBag(bag);
        Assertions.assertEquals(bag, lockerRobotManager.takeOutBag(ticket));
    }

    @Test
    public void should_take_out_fail_when_manager_take_out_bag_given_manager_two_lockers_and_no_robot_and_invalid_ticket() {
        List<Locker> lockers = Arrays.asList(LockerUtil.getEmptyLocker(), LockerUtil.getEmptyLocker());
        LockerRobotManager lockerRobotManager = new LockerRobotManager(lockers, Collections.emptyList());
        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.storeBag(bag);
        Assertions.assertThrows(BadTicketException.class, () -> lockerRobotManager.takeOutBag(new Ticket()));
    }

    @Test
    public void should_take_out_successfully_when_manager_take_out_bag_given_manager_no_locker_and_two_robots_and_valid_ticket() {
        List<LockerRobotBase> robots = Arrays.asList(LockerUtil.getNotFullPrimaryLockerRobot(), LockerUtil.getNotFullPrimaryLockerRobot());
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Collections.emptyList(), robots);
        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.storeBag(bag);
        Assertions.assertEquals(bag, lockerRobotManager.takeOutBag(ticket));
    }


    @Test
    public void should_take_out_fail_when_manager_take_out_bag_given_manager_no_locker_and_two_robots_and_invalid_ticket(){
        List<LockerRobotBase> robots = Arrays.asList(LockerUtil.getNotFullPrimaryLockerRobot(), LockerUtil.getNotFullPrimaryLockerRobot());
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Collections.emptyList(), robots);
        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.storeBag(bag);
        Assertions.assertThrows(BadTicketException.class, () -> lockerRobotManager.takeOutBag(new Ticket()));
    }

    @Test
    public void should_take_out_successfully_when_manager_take_out_bag_given_manager_one_locker_and_one_robot_and_valid_ticket(){
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Collections.singletonList(LockerUtil.getEmptyLocker()),
                Collections.singletonList(LockerUtil.getNotFullPrimaryLockerRobot()));
        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.storeBag(bag);
        Assertions.assertEquals(bag, lockerRobotManager.takeOutBag(ticket));
    }

    @Test
    public void should_take_out_fail_when_manager_take_out_bag_given_manager_one_locker_and_one_robot_and_invalid_ticket(){
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Collections.singletonList(LockerUtil.getEmptyLocker()),
                Collections.singletonList(LockerUtil.getNotFullPrimaryLockerRobot()));
        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.storeBag(bag);
        Assertions.assertThrows(BadTicketException.class, () -> lockerRobotManager.takeOutBag(new Ticket()));
    }
}
