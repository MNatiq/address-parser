package de.friday.exception;

public class NullOrEmptyAddressException extends RuntimeException {
    public NullOrEmptyAddressException() {
        super("Address is null or empty and can not be parsed");
    }
}
