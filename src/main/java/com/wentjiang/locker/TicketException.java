package com.wentjiang.locker;

public class TicketException extends RuntimeException {

    public static final String ERROR_MESSAGE_BAD_TICKET = "ticket is bad";
    public static final String ERROR_MESSAGE_USED_TICKET = "ticket has been used";

    public TicketException(String message) {
        super(message);
    }
}
