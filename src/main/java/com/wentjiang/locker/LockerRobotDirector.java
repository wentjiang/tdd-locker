package com.wentjiang.locker;

import java.util.List;

public class LockerRobotDirector {

    private final List<LockerRobotManager> managers;

    public LockerRobotDirector(List<LockerRobotManager> managers) {
        this.managers = managers;
    }

    public String statisticalForm() {
        StringBuilder result = new StringBuilder();
        for (LockerRobotManager manager : managers){
            result.append(manager.statisticalForm(0));
        }
        return result.toString();
    }
}
