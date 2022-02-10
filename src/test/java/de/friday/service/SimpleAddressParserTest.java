package de.friday.service;

import de.friday.exception.MatcherNotFoundException;
import de.friday.exception.NullOrEmptyAddressException;
import de.friday.service.impl.SimpleAddressParser;
import org.junit.jupiter.api.Test;
import static de.friday.TestInputs.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

public class SimpleAddressParserTest {

  @Test
  public void should_parse_simple_address() {
    SimpleAddressParser simpleAddressParser = new SimpleAddressParser();
    final String[] addressParsed = simpleAddressParser.parse(SIMPLE_ADDRESS);
    assertThat(addressParsed)
        .as("The size of array shoud be 2")
        .hasSize(2)
        .as("The array should contains Auf der Vogelwiese and 23 b")
        .containsExactly("Auf der Vogelwiese", "23 b");
  }

  @Test
  public void should_throw_MatcherNotFoundException() {
    SimpleAddressParser simpleAddressParser = new SimpleAddressParser();
    Throwable thrown = catchThrowable(() -> simpleAddressParser.parse(NO_PARSER_ADDRESS));
    assertThat(thrown).isExactlyInstanceOf(MatcherNotFoundException.class);
    assertThat(thrown).hasMessageContaining("No Matcher found for address");
  }

  @Test
  public void with_address_null_should_throw_AddressNullOrEmptyException() {
    SimpleAddressParser simpleAddressParser = new SimpleAddressParser();
    Throwable thrown = catchThrowable(() -> simpleAddressParser.parse(NULL_ADDRESS));
    assertThat(thrown).isExactlyInstanceOf(NullOrEmptyAddressException.class);
    assertThat(thrown).hasMessageContaining("Address is null or empty and can not be parsed");
  }
}
