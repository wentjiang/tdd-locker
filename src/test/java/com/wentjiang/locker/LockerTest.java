package com.wentjiang.locker;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LockerTest {

    private static int DEFAULT_CAPACITY = 10;
    ;

    @Test
    public void test() {
        System.out.println("hello test");
    }

    //given 没满的locker,需要存的包  when 存包 then 生成小票
    @Test
    public void should_generate_ticket_WHEN_store_bag_GIVEN_locker_not_full_and_bag() {
        Locker locker = new Locker(DEFAULT_CAPACITY);
        Bag bag = new Bag("test name");
        Ticket ticket = locker.storeBag(bag);
        Assert.assertNotNull(ticket);
    }

    //given 满了的locker,需要存的包  when 存包 then 不生成小票,提示储物柜已满
    @Test
    public void should_remind_locker_is_full_WHEN_store_bag_GIVEN_full_locker_and_bag() {
        Locker locker = new Locker(DEFAULT_CAPACITY);
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            locker.storeBag(new Bag("test name " + (i + 1)));
        }
        Bag bag = new Bag("test name " + 11);
        CapacityFullException thrown =
                Assertions.assertThrows(CapacityFullException.class, () -> locker.storeBag(bag));
        Assertions.assertTrue(thrown.getMessage().contains("the locker is full"));
    }

    //given 已存了包的locker,未使用的正确的小票 when 取包 then 验证通过
    @Test
    public void should_verify_passed_WHEN_take_bag_GIVEN_correct_ticket() {
        Locker locker = new Locker(DEFAULT_CAPACITY);
        Bag bag = new Bag("test name " + 1);
        Ticket ticket = locker.storeBag(bag);
        boolean verifyResult = locker.takeOut(ticket);
        Assertions.assertTrue(verifyResult);
    }

}
