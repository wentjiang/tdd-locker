package com.wentjiang.locker;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LockerRobotDirectorTest {

    @Test
    public void should_output_right_result_when_statistical_form_given_director_manage_1_manager_that_manage_two_lockers() {
        String exceptedResult =
                "" +
                        "M 6 20\n" +
                        "  L 2 10\n" +
                        "  L 4 10\n";
        List<Locker> lockers = Arrays.asList(LockerUtil.getStoredLocker(10 - 2, 10),
                LockerUtil.getStoredLocker(10 - 4, 10));
        LockerRobotManager manager = new LockerRobotManager(lockers, Collections.emptyList());
        LockerRobotDirector director = new LockerRobotDirector(Collections.singletonList(manager));
        String result = director.statisticalForm();
        Assertions.assertEquals(exceptedResult, result);
    }

    @Test
    public void should_output_right_result_when_statistical_form_given_director_manage_1_manager_and_1_locker() {
        String exceptedResult =
                "" +
                        "M 6 20\n" +
                        "  L 2 10\n" +
                        "  R 4 10\n" +
                        "    L 4 10\n";
        LockerRobotDirector director = new LockerRobotDirector(
                Collections.singletonList(new LockerRobotManager(Collections.singletonList(LockerUtil.getStoredLocker(10 - 2, 10)),
                        Collections.singletonList(new PrimaryLockerRobot(Collections.singletonList(LockerUtil.getStoredLocker(10 - 4, 10)))))));
        String result = director.statisticalForm();
        Assertions.assertEquals(exceptedResult, result);
    }

    @Test
    public void should_output_right_result_when_statistical_form_given_director_manage_two_robots() {
        String exceptedResult =
                "" +
                        "M 12 40\n" +
                        "  R 4 20\n" +
                        "    L 2 10\n" +
                        "    L 2 10\n" +
                        "  R 8 20\n" +
                        "    L 4 10\n" +
                        "    L 4 10\n";
        LockerRobotManager manager = new LockerRobotManager(Collections.emptyList(),
                Arrays.asList(new PrimaryLockerRobot(Arrays.asList(
                        LockerUtil.getStoredLocker(10 - 2, 10),
                        LockerUtil.getStoredLocker(10 - 2, 10))),
                        new PrimaryLockerRobot(Arrays.asList(
                                LockerUtil.getStoredLocker(10 - 4, 10),
                                LockerUtil.getStoredLocker(10 - 4, 10)))));
        LockerRobotDirector director = new LockerRobotDirector(Collections.singletonList(manager));
        String result = director.statisticalForm();
        Assertions.assertEquals(exceptedResult, result);
    }

    @Test
    public void should_output_right_result_when_statistical_form_given_director_manage_one_manager_that_manage_two_lockers_and_not_manager_one_locker_and_one_robot() {
        String exceptedResult =
                "" +
                        "M 6 20\n" +
                        "  L 2 10\n" +
                        "  L 4 10\n";
        LockerRobotManager manager = new LockerRobotManager(
                Arrays.asList(LockerUtil.getStoredLocker(10 - 2, 10),
                        LockerUtil.getStoredLocker(10 - 4, 10)),
                Collections.emptyList());
        Locker unManagedLocker = LockerUtil.getStoredLocker(10 - 3, 10);
        LockerRobotBase unManagedRobot = new PrimaryLockerRobot(Collections.singletonList(LockerUtil.getStoredLocker(10 - 5, 10)));
        LockerRobotDirector director = new LockerRobotDirector(Collections.singletonList(manager));
        String result = director.statisticalForm();
        Assertions.assertEquals(exceptedResult, result);
    }

    @Test
    public void should() {
        String exceptedResult =
                "" +
                        "M 2 10\n" +
                        "  L 2 10\n" +
                        "M 2 10\n" +
                        "  L 2 10\n";
        LockerRobotManager manager1 = new LockerRobotManager(
                Collections.singletonList(LockerUtil.getStoredLocker(10-2,10)),
                Collections.emptyList());
        LockerRobotManager manager2 = new LockerRobotManager(
                Collections.singletonList(LockerUtil.getStoredLocker(10-2,10)),
                Collections.emptyList());
        LockerRobotDirector director = new LockerRobotDirector(Arrays.asList(manager1,manager2));
        String result = director.statisticalForm();
        Assertions.assertEquals(exceptedResult, result);
    }
}
