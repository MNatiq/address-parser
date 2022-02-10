package de.friday.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import static de.friday.utils.AddressRegexUtils.INTERNATIONAL_ADDRESS_WITH_TEXT_NUMBER;

@Slf4j
@Component("numberTextParser")
public class AddressWithNumberTextParser extends BaseAddressParser {

  public AddressWithNumberTextParser() {
    super(INTERNATIONAL_ADDRESS_WITH_TEXT_NUMBER);
  }

  @Override
  protected String[] parseAddress() {
    final String[] addressParsed =
        Arrays.stream(address.split(INTERNATIONAL_ADDRESS_WITH_TEXT_NUMBER))
            .map(String::trim)
            .toArray(String[]::new);

    log.debug("Parsed address {}", Arrays.toString(addressParsed));
    return addressParsed;
  }
}
