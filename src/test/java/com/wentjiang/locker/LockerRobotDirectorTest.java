package com.wentjiang.locker;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LockerRobotDirectorTest {

    @Test
    public void should_output_right_result_when_statistical_form_given_director_manage_1_manager_that_manage_two_lockers() {
        String exceptedResult = "M 6 20\n" +
                "  L 2 10\n" +
                "  L 4 10\n";
        List<Locker> lockers = Arrays.asList(LockerUtil.getStoredLocker(10 - 2, 10),
                LockerUtil.getStoredLocker(10 - 4, 10));
        LockerRobotManager manager = new LockerRobotManager(lockers, Collections.emptyList());
        LockerRobotDirector director = new LockerRobotDirector(Collections.singletonList(manager));
        String result = director.statisticalForm();
        Assertions.assertEquals(exceptedResult,result);
    }


}
