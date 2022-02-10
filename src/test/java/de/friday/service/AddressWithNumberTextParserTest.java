package de.friday.service;

import de.friday.service.impl.AddressWithNumberTextParser;
import org.junit.jupiter.api.Test;
import static de.friday.TestInputs.TEXT_NUMBER_ADDRESS;
import static org.assertj.core.api.Assertions.assertThat;

public class AddressWithNumberTextParserTest {

  @Test
  public void should_parse_an_address_with_text_number() {
    AddressWithNumberTextParser parseAddressWithTextNumber = new AddressWithNumberTextParser();
    final String[] addressParsed = parseAddressWithTextNumber.parse(TEXT_NUMBER_ADDRESS);
    assertThat(addressParsed)
        .as("The size of array should be 2")
        .hasSize(2)
        .as("The array should contains Calle 39 and No 1540")
        .containsExactly("Calle 39", "No 1540");
  }
}
