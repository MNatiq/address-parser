package de.friday.exception;

public class ParserNotFoundException extends RuntimeException {
    public ParserNotFoundException(String address) {
        super(String.format("No parser found for address %s", address));
    }
}