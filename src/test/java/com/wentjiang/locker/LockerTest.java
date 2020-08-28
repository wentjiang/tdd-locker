package com.wentjiang.locker;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.wentjiang.locker.TicketException.ERROR_MESSAGE_BAD_TICKET;
import static com.wentjiang.locker.TicketException.ERROR_MESSAGE_USED_TICKET;

public class LockerTest {

    private static int DEFAULT_CAPACITY = 10;
    private Locker locker;

    @BeforeEach
    public void init() {
        locker = new Locker(DEFAULT_CAPACITY);
    }

    @Test
    public void test() {
        System.out.println("hello test");
    }

    //given 没满的locker,需要存的包  when 存包 then 生成小票
    @Test
    public void should_generate_ticket_WHEN_store_bag_GIVEN_locker_not_full_and_bag() {
        Bag bag = new Bag("test name");
        Ticket ticket = locker.storeBag(bag);
        Assert.assertNotNull(ticket);
    }

    //given 满了的locker,需要存的包  when 存包 then 不生成小票,提示储物柜已满
    @Test
    public void should_remind_locker_is_full_WHEN_store_bag_GIVEN_full_locker_and_bag() {
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            locker.storeBag(new Bag("test name " + (i + 1)));
        }
        Bag bag = new Bag("test name " + 11);
        CapacityFullException thrown =
                Assertions.assertThrows(CapacityFullException.class, () -> locker.storeBag(bag));
        Assertions.assertTrue(thrown.getMessage().contains(CapacityFullException.ERROR_MESSAGE_CAPACITY_FULL));
    }

    //given 已存了包的locker,未使用的正确的小票 when 取包 then 验证通过
    @Test
    public void should_verify_passed_WHEN_take_bag_GIVEN_correct_ticket() {
        Bag bag = new Bag("test name " + 1);
        Ticket ticket = locker.storeBag(bag);
        boolean verifyResult = locker.takeOutBag(ticket);
        Assertions.assertTrue(verifyResult);
    }

    //given locker,错误的小票 when 取包 then 验证失败,提示票无效
    @Test
    public void should_verify_not_passed_WHEN_take_bag_GIVEN_bad_ticket() {
        Ticket badTicket = new Ticket("bad ticket");
        TicketException thrown =
                Assertions.assertThrows(TicketException.class, () -> locker.takeOutBag(badTicket));
        Assertions.assertTrue(thrown.getMessage().contains(ERROR_MESSAGE_BAD_TICKET));
    }

    //given locker,已经被验证过的小票 when 取包 then 验证失败,提示票无效
    @Test
    public void should_verify_not_passed_WHEN_take_bag_GIVEN_used_ticket(){
        Bag bag = new Bag("test name " + 1);
        Ticket ticket = locker.storeBag(bag);
        boolean verifyResult = locker.takeOutBag(ticket);
        Assertions.assertTrue(true);
        TicketException thrown =
                Assertions.assertThrows(TicketException.class, () -> locker.takeOutBag(ticket));
        Assertions.assertTrue(thrown.getMessage().contains(ERROR_MESSAGE_USED_TICKET));
    }

}
