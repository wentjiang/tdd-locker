package com.wentjiang.locker;

public class LockerUtil {
    public static final int DEFAULT_CAPACITY = 10;
    public static final Locker emptyLocker1;
    public static final Locker emptyLocker2;
    public static final Locker fullLocker1;
    public static final Locker fullLocker2;

    static {
        emptyLocker1 = new Locker(DEFAULT_CAPACITY);
        emptyLocker2 = new Locker(DEFAULT_CAPACITY);
        fullLocker1 = new Locker(DEFAULT_CAPACITY);
        fullLocker2 = new Locker(DEFAULT_CAPACITY);
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            fullLocker1.storeBag(new Bag());
            fullLocker2.storeBag(new Bag());
        }
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
}
