package com.wentjiang.locker;

import java.util.Arrays;
import java.util.Collections;

public class LockerUtil {
    public static final int DEFAULT_CAPACITY = 10;

    public static Locker getEmptyLocker() {
        return new Locker(DEFAULT_CAPACITY);
    }

    public static Locker getFullLocker() {
        Locker fullLocker = new Locker(DEFAULT_CAPACITY);
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            fullLocker.storeBag(new Bag());
        }
        return fullLocker;
    }

    public static Locker getStoredLocker(int storeNum, int capacity) {
        if (storeNum > capacity) {
            storeNum = capacity;
        }
        Locker locker = new Locker(capacity);
        for (int i = 0; i < storeNum; i++) {
            locker.storeBag(new Bag());
        }
        return locker;
    }

    public static LockerRobotBase getNotFullPrimaryLockerRobot() {
        return new PrimaryLockerRobot(Collections.singletonList(getEmptyLocker()));
    }

    public static LockerRobotBase getFullPrimaryLockerRobot() {
        return new PrimaryLockerRobot(Collections.singletonList(getFullLocker()));
    }
}
