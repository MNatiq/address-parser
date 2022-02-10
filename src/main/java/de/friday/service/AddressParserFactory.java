package de.friday.service;

import de.friday.exception.NullOrEmptyAddressException;
import de.friday.exception.ParserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static de.friday.utils.AddressRegexUtils.*;

@Component
public class AddressParserFactory {

  private final AddressParser numberTextParser;
  private final AddressParser internationalAddressParser;
  private final AddressParser simpleAddressParser;

  @Autowired
  public AddressParserFactory(
      @Qualifier("numberTextParser") AddressParser numberTextParser,
      @Qualifier("internationalAddressParser") AddressParser internationalAddressParser,
      @Qualifier("simpleAddressParser") AddressParser simpleAddressParser) {
    this.numberTextParser = numberTextParser;
    this.internationalAddressParser = internationalAddressParser;
    this.simpleAddressParser = simpleAddressParser;
  }

  public AddressParser getParser(String address) {
    if (address == null || address.isEmpty()) {
      throw new NullOrEmptyAddressException();
    }
    if (getMatcher(ADDRESS_WITH_TEXT_NUMBER, address).find()) {
      return numberTextParser;
    } else if (getMatcher(SIMPLE_ADDRESS, address).find()) {
      return simpleAddressParser;
    } else if (getMatcher(INTERNATIONAL_ADDRESS, address).find()) {
      return internationalAddressParser;
    } else throw new ParserNotFoundException(address);
  }
}
