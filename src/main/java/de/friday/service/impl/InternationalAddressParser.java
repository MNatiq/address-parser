package de.friday.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import static de.friday.utils.AddressRegexUtils.INTERNATIONAL_ADDRESS;

@Slf4j
@Component("internationalAddressParser")
public class InternationalAddressParser extends BaseAddressParser {
  public InternationalAddressParser() {
    super(INTERNATIONAL_ADDRESS);
  }

  @Override
  protected String[] parseAddress() {
    String[] addressParsed = new String[2];
    for (int i = 1; i <= matcher.groupCount(); i++) {
      final String data = matcher.group(i).trim();
      if (NumberUtils.isNumber(data)) {
        addressParsed[1] = data;
      }
      addressParsed[0] = data;
    }
    log.debug("Parsed address {}", Arrays.toString(addressParsed));
    return addressParsed;
  }
}
