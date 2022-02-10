package de.friday.service.impl;

import de.friday.exception.MatcherNotFoundException;
import de.friday.service.AddressParser;
import de.friday.utils.AddressRegexUtils;
import lombok.extern.slf4j.Slf4j;
import java.util.regex.Matcher;

@Slf4j
public abstract class BaseAddressParser implements AddressParser {

  private final String REGEX;
  protected String address;
  protected Matcher matcher;

  public BaseAddressParser(String regex) {
    this.REGEX = regex;
  }

  /**
   * @param address - String address to to be parsed - Number and Street
   * @return String[] - the address parsed as Array String - Position 0 Street, Av, etc.. Position 1
   *     - Address Number
   * @throws MatcherNotFoundException
   */
  @Override
  public String[] parse(String address) {

    log.debug("Address received to parse {}", address);
    validateAndCleanUp(address);
    this.address = address;
    matcher = AddressRegexUtils.getMatcher(REGEX, address);

    if (matcher.find()) {
      log.debug("Matcher found for address {}", address);
      return parseAddress();
    }
    log.warn("No Matcher found for address {}", address);
    throw new MatcherNotFoundException(address);
  }

  protected abstract String[] parseAddress();
}
