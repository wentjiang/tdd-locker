package com.wentjiang.locker;

public class TicketException extends RuntimeException {

    public static final String ERROR_MESSAGE_TICKET = "ticket is bad";

    public TicketException(String message) {
        super(message);
    }
}
