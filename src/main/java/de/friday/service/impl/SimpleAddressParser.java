package de.friday.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import static de.friday.utils.AddressRegexUtils.SIMPLE_ADDRESS;

@Slf4j
@Component("simpleAddressParser")
public class SimpleAddressParser extends BaseAddressParser {

    public SimpleAddressParser() {
        super(SIMPLE_ADDRESS);
    }

    @Override
    protected String[] parseAddress() {

        String[] addressParsed = new String[2];
        for (int i = 1; i <= matcher.groupCount(); i++) {
            addressParsed[i - 1] = matcher.group(i).trim();
        }
        log.debug("Parsed address {}", Arrays.toString(addressParsed));
        return addressParsed;
    }
}