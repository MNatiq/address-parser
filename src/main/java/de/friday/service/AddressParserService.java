package de.friday.service;

import de.friday.exception.NullOrEmptyAddressException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import static de.friday.utils.AddressRegexUtils.removeSpecialCharacters;

@Slf4j
@RequiredArgsConstructor
@Service
public class AddressParserService {

    private final AddressParserFactory addressParserFactory;

    public String[] parseAddress(String address){
        if (address == null || address.isEmpty()) {
            throw new NullOrEmptyAddressException();
        }

        address = removeSpecialCharacters(address);
        log.debug("Address received to parse {}", address);
        AddressParser addressParser = addressParserFactory.getParser(address);
        final String[] addressParsed = addressParser.parse(address);
        log.debug("Parsed address {}", Arrays.toString(addressParsed));
        return addressParsed;
    }

}
