package de.friday.service;

import de.friday.service.impl.InternationalAddressParser;
import org.junit.jupiter.api.Test;

import static de.friday.TestInputs.INTERNATIONAL_ADDRESS;
import static org.assertj.core.api.Assertions.assertThat;
public class InternationalAddressParserTest {



    @Test
    public void should_parse_an_international_address_() {
        InternationalAddressParser parseInternationalAddress = new InternationalAddressParser();
        final String[] addressParsed = parseInternationalAddress.parse(INTERNATIONAL_ADDRESS);
        assertThat(addressParsed)
                .as("The size of array should be 2")
                .hasSize(2)
                .as("The array should contains Broadway Av and 200")
                .containsExactly("Broadway Av", "200");
    }
}
