package de.friday.service;

import de.friday.exception.NullOrEmptyAddressException;
import de.friday.utils.AddressRegexUtils;

public interface AddressParser {

    String[] parse(String address);

    default String validateAndCleanUp(String address) throws NullOrEmptyAddressException {
        if (address == null || address.isEmpty()) {
            throw new NullOrEmptyAddressException();
        }
        return AddressRegexUtils.removeSpecialCharacters(address);
    }
}
