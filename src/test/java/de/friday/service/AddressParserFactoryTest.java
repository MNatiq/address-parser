package de.friday.service;

import de.friday.exception.NullOrEmptyAddressException;
import de.friday.exception.ParserNotFoundException;
import de.friday.service.impl.AddressWithNumberTextParser;
import de.friday.service.impl.InternationalAddressParser;
import de.friday.service.impl.SimpleAddressParser;
import org.junit.jupiter.api.Test;
import static de.friday.TestInputs.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class AddressParserFactoryTest {

    private AddressParserFactory addressParserFactory =
            new AddressParserFactory(
                    new AddressWithNumberTextParser(),
                    new InternationalAddressParser(),
                    new SimpleAddressParser());

    @Test
    public void when_address_with_text_number_should_return_AddressWithNumberTextParser_class() {
        final AddressParser addressParser = addressParserFactory.getParser(TEXT_NUMBER_ADDRESS);
        assertThat(addressParser)
                .as("addressParser should not be null")
                .isNotNull()
                .as("The instance of address parser should be AddressWithNumberTextParser")
                .isExactlyInstanceOf(AddressWithNumberTextParser.class);
    }

    @Test
    public void when_address_is_simple_should_return_SimpleAddressParser_class() {
        final AddressParser addressParser = addressParserFactory.getParser(SIMPLE_ADDRESS);
        assertThat(addressParser)
                .as("addressParser should not be null")
                .isNotNull()
                .as("The instance of address parser should be SimpleAddressParser")
                .isExactlyInstanceOf(SimpleAddressParser.class);
    }

    @Test
    public void when_address_is_international_should_return_InternationalAddressParser_class() {
        final AddressParser addressParser = addressParserFactory.getParser(BROADWAY_AV_ADDRESS);

        assertThat(addressParser)
                .as("addressParser should not be null")
                .isNotNull()
                .as("The instance of address parser should be InternationalAddressParser")
                .isExactlyInstanceOf(InternationalAddressParser.class);
    }

    @Test
    public void when_no_parser_found_for_address_should_throw_NoParserFoundException() {
        Throwable thrown = catchThrowable(() -> addressParserFactory.getParser(NO_PARSER_ADDRESS));
        assertThat(thrown).isExactlyInstanceOf(ParserNotFoundException.class);
        assertThat(thrown).hasMessageContaining("No parser found for address");
    }

    @Test
    public void when_address_is_null_or_empty_should_throw_AddressNullOrEmptyException() {
        Throwable thrown = catchThrowable(() -> addressParserFactory.getParser(NULL_ADDRESS));
        assertThat(thrown).isExactlyInstanceOf(NullOrEmptyAddressException.class);
        assertThat(thrown).hasMessageContaining("Address is null or empty and can not be parsed");

        thrown = catchThrowable(() -> addressParserFactory.getParser(EMPTY_ADDRESS));
        assertThat(thrown).isExactlyInstanceOf(NullOrEmptyAddressException.class);
        assertThat(thrown).hasMessageContaining("Address is null or empty and can not be parsed");
    }
}
