package de.friday.exception;

public class MatcherNotFoundException extends RuntimeException {
    public MatcherNotFoundException(String address) {
        super(String.format("No Matcher found for address %s", address));
    }
}

