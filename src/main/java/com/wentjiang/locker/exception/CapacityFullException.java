package com.wentjiang.locker.exception;

public class CapacityFullException extends RuntimeException {

    public static final String ERROR_MESSAGE_CAPACITY_FULL = "the locker is full";

    public CapacityFullException(String message) {
        super(message);
    }
}
