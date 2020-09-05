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

}
